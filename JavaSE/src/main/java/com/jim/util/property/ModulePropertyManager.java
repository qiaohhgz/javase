package com.jim.util.property;

import java.io.*;
import java.util.*;

/**
 * Created with IntelliJ IDEA.
 * User: JimQiao
 * Date: 4/25/13
 * Time: 1:10 PM
 * To change this template use File | Settings | File Templates.
 */
public class ModulePropertyManager {
    private static String[] arrEnvironments = null;
    private static HashMap<String, Boolean> mpEnvironments = null;

    static {
        arrEnvironments = new String[]{"dev", "uat", "stg", "prd-kop", "prd-cdr"};
        mpEnvironments = new HashMap<String, Boolean>();
        for (String env : arrEnvironments) {
            mpEnvironments.put(env, Boolean.TRUE);
        }
    }

    private static final String PROPS_SUFFIX = ".properties";
    private static final String PROPS_STATIC_SUFFIX = ".static.properties";
    private static final String PROPS_DYNAMIC_SUFFIX = ".dynamic.properties";

    /**
     * The entry point of the ModulePropertyManager.
     * <p/>
     * This class is typically called from ANT and two
     * input arguments are supplied:
     * - the module root
     * - the build environment
     * <p/>
     * Input arguments examples:
     * <p/>
     * 1)
     * moduleRoot = "C:/webreach/java/trunk/diad"
     * buildEnv = "dev"
     * <p/>
     * 2)
     * moduleRoot = "C:/webreach/java/trunk/salesportal"
     * buildEnv = "uat"
     *
     * @param args the program arguments
     */
    public static void main(String[] args) {
        String propsDir = "";
        String buildEnv = "";

        String distDir = "";
        if (args != null && args.length >= 1 && "work".equalsIgnoreCase(args[0])) {
            propsDir = args[1];
            buildEnv = args[2];
            ModulePropertyManager mgr = new ModulePropertyManager();
            mgr.work(propsDir, buildEnv);
        } else if (args != null && args.length >= 1 && "rename".equalsIgnoreCase(args[0])) {
            distDir = args[1];
            ModulePropertyManager mgr = new ModulePropertyManager();
            mgr.rename(distDir);
        } else {
            System.out.println("Usage: ModulePropertyManager <props_dir> <build_env>");
        }
    }

    public void rename(String sDir) {
        File dir = new File(sDir);
        File[] fls = dir.listFiles();
        for (File f : fls) {
            if (!f.isDirectory()) {
                for (String e : arrEnvironments) {
                    String suffix = "-" + e + ".properties";
                    String fName = f.getName();
                    if (fName.endsWith(suffix)) {
                        boolean deleted = f.delete();
                        if (!deleted) {
                            throw new IllegalStateException("Failed to delete " + f.getAbsolutePath());
                        }
                        break;
                    }
                }
            } else {
                rename(f.getAbsolutePath());
            }
        }
    }

    /**
     * The entry point of the ModulePropertyManager.
     * This class is typically called from ANT and two
     * input arguments are supplied:
     * 1) the module root,
     * 2) the build environment.
     *
     * @param propDir  the props directory
     * @param buildEnv the build environment (one of dev, uat, stg, prd-kop, prd-cdr)
     */
    public void work(String propDir, String buildEnv) {
        try {
            boolean isEnvValid = mpEnvironments.containsKey(buildEnv);
            if (!isEnvValid) {
                throw new IllegalStateException("Invalid Build Environment " + buildEnv + ".");
            }

            System.out.println("Current Dir = " + (new File(".")).getAbsolutePath());
            System.out.println("Properties Dir = " + propDir);
            System.out.println("Build Environment Code = " + buildEnv);

            File dirPropsFiles = new File(propDir);
            File[] propFiles = getPropertyFiles(dirPropsFiles.listFiles());
            printPropertyFiles(propFiles);
            ArrayList<File[]> couples = validatePropertyFilesCouples(propFiles);
            for (File[] fc : couples) {
                for (File fProp : fc) {
                    if (isDynamicPropFile(fProp)) {
                        validatePropertyFilesProperties(fProp);
                    }
                }
            }

            for (String e : arrEnvironments) {
                for (File[] fc : couples) {
                    File fa = fc[0];
                    File fb = fc[1];
                    if (isStaticPropFile(fa)) {
                        mergePropertyFiles(fa, fb, e, buildEnv);
                    } else if (isDynamicPropFile(fa)) {
                        mergePropertyFiles(fb, fa, e, buildEnv);
                    } else {
                        throw new IllegalStateException("Bad program state #5. Check the program logic.");
                    }
                }
            }

        } catch (Exception ex) {
            throw new IllegalStateException(ex);
        }
    }

    private static Properties loadProperties(File inputFile) {
        FileInputStream fis = null;
        try {
            fis = new FileInputStream(inputFile);
            Properties p = new Properties();
            p.load(fis);
            return p;
        } catch (Exception ex) {
            return null;
        } finally {
            if (fis != null) {
                try {
                    fis.close();
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        }
    }

    private static boolean storeProperties(Properties p, File outputFile) {
        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(outputFile);
            Properties srt = new SortedProperties();
            Enumeration en = p.propertyNames();
            while (en.hasMoreElements()) {
                String key = (String) en.nextElement();
                srt.setProperty(key, p.getProperty(key));
            }
            srt.store(fos, "Auto-Saved - ModulePropertyManager");
            return true;
        } catch (Exception ex) {
            return false;
        } finally {
            if (fos != null) {
                try {
                    fos.close();
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        }
    }

    private static boolean copyFile(File fSource, File fTarget) {
        boolean result = true;
        FileInputStream fis = null;
        FileOutputStream fos = null;
        byte[] buffer = new byte[128 * 1024];
        int cnt = 0;
        try {
            fis = new FileInputStream(fSource);
            fos = new FileOutputStream(fTarget);
            while ((cnt = fis.read(buffer)) != -1) {
                fos.write(buffer, 0, cnt);
            }
            result = true;
        } catch (Exception ex) {
            ex.printStackTrace();
            result = false;
        } finally {
            try {
                if (fis != null) fis.close();
                if (fos != null) fos.close();
            } catch (IOException e) {
                e.printStackTrace();
                result = false;
            }
        }
        return result;
    }

    private static void mergePropertyFiles(File fStaticProps, File fDynamicProps, String env, String buildEnv) {
        String sMainPropFileNameStatic = getMainPropFileName(fStaticProps);
        String sMainPropFileNameDynamic = getMainPropFileName(fDynamicProps);
        if (!sMainPropFileNameStatic.equalsIgnoreCase(sMainPropFileNameDynamic)) {
            throw new IllegalStateException("Bad program state #4. Check the program logic.");
        }
        File dirModuleConfig = fStaticProps.getParentFile();
        File fAllProps = new File(dirModuleConfig, sMainPropFileNameStatic + "-" + env + PROPS_SUFFIX);

        Properties pStatic = loadProperties(fStaticProps);
        Properties pDynamic = loadProperties(fDynamicProps);
        Properties result = unite(env, pStatic, pDynamic);
        storeProperties(result, fAllProps);
        File fBuildEnvProps = new File(dirModuleConfig, sMainPropFileNameStatic + PROPS_SUFFIX);
        if (env.equals(buildEnv)) {
            try {
                if (fBuildEnvProps.exists()) {
                    boolean deleted = fBuildEnvProps.delete();
                    if (!deleted) {
                        throw new IllegalStateException
                                ("Failed to delete " + fBuildEnvProps.getAbsolutePath());
                    }
                }
                copyFile(fAllProps, fBuildEnvProps);
            } catch (Exception ex) {
                throw new IllegalStateException("Error while copying property file " +
                        fAllProps.getAbsolutePath() + ".", ex);
            }
        }

        System.out.println("File " + fAllProps.getAbsolutePath() + " saved successfully.");
    }

    private static String getMainPropFileName(File f) {
        if (isStaticPropFile(f)) {
            String fName = f.getName();
            return fName.substring(0, fName.lastIndexOf(PROPS_STATIC_SUFFIX));
        } else if (isDynamicPropFile(f)) {
            String fName = f.getName();
            return fName.substring(0, fName.lastIndexOf(PROPS_DYNAMIC_SUFFIX));
        } else {
            throw new IllegalStateException("Bad program state #3. Check the program logic.");
        }
    }

    private static void printPropertyFiles(File[] propFiles) {
        for (File f : propFiles) {
            System.out.println("Property file found: " + f.getAbsolutePath());
        }
    }

    private static File[] getPropertyFiles(File[] arr) {
        ArrayList<File> lst = new ArrayList<File>();
        for (File f : arr) {
            if (f.getName().endsWith(PROPS_SUFFIX)) {
                lst.add(f);
            }
        }
        File[] res = new File[lst.size()];
        res = lst.toArray(res);
        return res;
    }

    private static String[] split(String s, char delim) {
        StringTokenizer st = new StringTokenizer(s, new String(new char[]{delim}));
        ArrayList<String> lst = new ArrayList<String>();
        while (st.hasMoreTokens()) {
            lst.add(st.nextToken());
        }
        String[] res = new String[lst.size()];
        res = lst.toArray(res);
        return res;
    }

    private static String getCounterPartName(String nm) {
        if ("static".equalsIgnoreCase(nm)) return "dynamic";
        else if ("dynamic".equalsIgnoreCase(nm)) return "static";
        else return nm;
    }

    private static boolean isStaticPropFile(File f) {
        return f.getName().endsWith(PROPS_STATIC_SUFFIX);
    }

    private static boolean isDynamicPropFile(File f) {
        return f.getName().endsWith(PROPS_DYNAMIC_SUFFIX);
    }

    private static String getCounterPartName(File f) {
        String[] nms = split(f.getName(), '.');
        StringBuilder sbCounterPartName = new StringBuilder();
        for (int i = 0; i < nms.length; i++) {
            sbCounterPartName.append(i == 0 ? "" : ".");
            sbCounterPartName.append(getCounterPartName(nms[i]));
        }
        return sbCounterPartName.toString();
    }

    private static ArrayList<File[]> validatePropertyFilesCouples(File[] files) {
        File[] res = null;
        ArrayList<File[]> result = new ArrayList<File[]>();
        HashMap<String, File> mp = new HashMap<String, File>();
        for (File f : files) {
            if (isStaticPropFile(f) || isDynamicPropFile(f)) {
                mp.put(f.getName(), f);
            }
        }
        while (mp.keySet().size() > 0) {
            Iterator<String> iter = mp.keySet().iterator();
            String fName = iter.next();
            File f = mp.get(fName);
            String fCounterPartName = getCounterPartName(f);
            if (mp.containsKey(fName) != mp.containsKey(fCounterPartName)) {
                if (isStaticPropFile(f)) {
                    throw new IllegalStateException("Property file " + f.getAbsolutePath() + " has no dynamic counterpart.");
                } else if (isDynamicPropFile(f)) {
                    throw new IllegalStateException("Property file " + f.getAbsolutePath() + " has no static counterpart.");
                } else {
                    throw new IllegalStateException("Bad program state #1. Check the program logic.");
                }
            } else {
                res = new File[2];
                res[0] = mp.get(fName);
                res[1] = mp.get(fCounterPartName);
                result.add(res);
                mp.remove(fName);
                mp.remove(fCounterPartName);
            }
        }
        if (mp.keySet().size() > 0) {
            throw new IllegalStateException("Bad program state #2. Check the program logic.");
        }

        return result;
    }

    /**
     * Given an environment-suffixed propName
     * returns the environment suffix.
     *
     * @param propName the prop name
     * @return the env suffix
     */
    private static String getEnvName(String propName) {
        int i = propName.lastIndexOf(".");
        if (i < 0) return "";
        else return propName.substring(i + 1);
    }

    /**
     * Given an environment-suffixed propName returns the
     * same propName with the environment suffix removed.
     *
     * @param propName the prop name
     * @return the prop name without the env suffix
     */
    private static String getPropName(String propName) {
        int i = propName.lastIndexOf(".");
        if (i < 0) return propName;
        else return propName.substring(0, i);
    }

    /**
     * Unites the static and dynamic properties
     * of the current module and returns them.
     *
     * @param env      the target env (dev, uat, stg, etc.)
     * @param pStatic  the static props
     * @param pDynamic the dynamic props
     * @return All props of the current module
     */
    private static Properties unite(String env, Properties pStatic, Properties pDynamic) {
        Properties result = new Properties();

        Properties p = pStatic;
        Enumeration en = p.propertyNames();
        while (en.hasMoreElements()) {
            String propName = (String) en.nextElement();
            result.setProperty(propName, p.getProperty(propName));
        }

        p = pDynamic;
        en = p.propertyNames();
        while (en.hasMoreElements()) {
            String propName = (String) en.nextElement();
            if (env.equals(getEnvName(propName))) {
                result.setProperty(getPropName(propName), p.getProperty(propName));
            }
        }

        return result;
    }

    /**
     * Validates the dynamic properties
     * of a particular WebReach module.
     *
     * @param fDynamicProps the file with dynamic properties which are being validated
     * @throws FileNotFoundException In case of file issues
     * @throws IOException           In case of file issues
     */
    private static void validatePropertyFilesProperties(File fDynamicProps) throws FileNotFoundException, IOException {
        FileInputStream fis = null;
        String fDynamicPropsFileName = "null";
        try {
            fDynamicPropsFileName = fDynamicProps.getName();
            fis = new FileInputStream(fDynamicProps);
            Properties pDynamic = new Properties();
            pDynamic.load(fis);
            Enumeration en = pDynamic.propertyNames();

            HashMap<String, Boolean> propNamesNoSuffix = new HashMap<String, Boolean>();
            HashMap<String, Boolean> propNamesYesSuffix = new HashMap<String, Boolean>();

            while (en.hasMoreElements()) {
                String propName = (String) en.nextElement();
                int i = propName.lastIndexOf(".");
                if (i < 0) {
                    throw new IllegalStateException("Property " + propName + " from " +
                            fDynamicPropsFileName + " is not environment qualified.");
                }
                String env = propName.substring(i + 1);
                if (!mpEnvironments.containsKey(env)) {
                    throw new IllegalStateException("Property " + propName + " from " + fDynamicPropsFileName +
                            " is qualified with an invalid environment " + env + ".");
                }
                propNamesNoSuffix.put(propName.substring(0, i), Boolean.TRUE);
                propNamesYesSuffix.put(propName, Boolean.TRUE);
            }

            for (String propName : propNamesNoSuffix.keySet()) {
                for (String env : arrEnvironments) {
                    if (!propNamesYesSuffix.containsKey(propName + "." + env)) {
                        throw new IllegalStateException("Property " + propName + " from " + fDynamicPropsFileName +
                                " defines no value for " + env + ".");
                    }
                }
            }
            System.out.println("The dynamic properties in " + fDynamicPropsFileName + " are valid.");
        } catch (IllegalStateException ex) {
            System.out.println("The dynamic properties in " + fDynamicPropsFileName + " are invalid.");
            throw ex;
        } finally {
            if (fis != null) fis.close();
        }
    }

    public static void buildDynamicProperties(File source) throws IOException {
        // Load properties
        Properties properties = new Properties();
        properties.load(new FileInputStream(source));

        //
        Map<String, String> map = new TreeMap<String, String>();
        for (Object key : properties.keySet()) {
            for (String env : arrEnvironments) {
                String newKey = String.format("%s.%s", key, env);
                String value = (String) properties.get(key);
                System.out.printf("Debug: newKey = [%s] value = [%s]\n", newKey, value);
                map.put(newKey, value);
            }
        }

        // Put
        SortedProperties dynamicProperties = new SortedProperties();
        dynamicProperties.putAll(map);
        String comments = "Auto-Saved - ModulePropertyManager";
        System.out.printf("Source Name:%s\n", source.getName());
        String dynamicFileName = String.format("%s%s", source.getName().split("\\.")[0], PROPS_DYNAMIC_SUFFIX);
        File dynamicFile = new File(source.getParent(), dynamicFileName);
        System.out.printf("Dynamic File Path: %s\n", dynamicFile.getPath());
        dynamicProperties.store(new FileOutputStream(dynamicFile), comments);
    }
}



