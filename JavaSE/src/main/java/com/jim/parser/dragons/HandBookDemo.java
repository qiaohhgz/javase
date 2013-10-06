package com.jim.parser.dragons;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.htmlparser.Node;
import org.htmlparser.Parser;
import org.htmlparser.filters.CssSelectorNodeFilter;
import org.htmlparser.nodes.TagNode;
import org.htmlparser.tags.LinkTag;
import org.htmlparser.util.ParserException;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CountDownLatch;

public class HandBookDemo {
    private static final Logger log = Logger.getLogger(HandBookDemo.class);
    private String basePath = "handbook";
    private String template;

    public HandBookDemo() throws Exception {
        String home = "http://www.51app.com/article/5036f6bfb67c88fa2400000f.html";
        List<String> links = parserAllLocation(home);
        for (Iterator<String> iterator = links.iterator(); iterator.hasNext(); ) {
            String text = iterator.next();
            String url = iterator.next();
            log.info("text = " + text + "  url = " + url);
            parserAllNode(url, basePath + "/" + text);
        }
        template = FileUtils.readFileToString(new File("handbook/template.html"));
        createHtml(template, basePath, basePath + "/index.html");
    }

    private void createHtml(String template, String in, String out) throws IOException {
        File[] folders = new File(in).listFiles(new FileFilter() {
            @Override
            public boolean accept(File dir) {
                return (dir.isDirectory() && dir.getName().startsWith("【"));
            }
        });
        StringBuffer sb = new StringBuffer();
        for (File file : folders) {
            File[] files = file.listFiles(new FileFilter() {
                @Override
                public boolean accept(File dir) {
                    return dir.getName().endsWith(".jpg");
                }
            });
            String parent = file.getName();
            String part = MessageFormat.format("<h3>{0}</h3>\n<div>", parent);
            log.info("part = " + parent);
            sb.append(part);
            for (File f : files) {
                String name = f.getName();
                BufferedImage read = ImageIO.read(f);
                int width = read.getWidth();
                int height = read.getHeight();
                part = MessageFormat.format("<img src=\"{0}/{1}\" height=\"{2}px\" width=\"{3}px\"/><br />\n", parent, name, height, width);
                log.info("part = " + part);
                sb.append(part);
            }
            sb.append("</div>\n");
        }

        template = template.replace("##Main##", sb.toString());
        BufferedWriter writer = new BufferedWriter(new FileWriter(new File(out)));
        writer.append(template);
        writer.flush();
        writer.close();
    }

    private List<String> parserAllLocation(String url) throws MalformedURLException, IOException, ParserException {
        List<String> list = new ArrayList<String>();
        URLConnection connection = new URL(url).openConnection();
        connection.setConnectTimeout(3000);
        Parser p = new Parser(connection);
        CssSelectorNodeFilter actionFilter = new CssSelectorNodeFilter("div[class=art_page] a");
        Node[] nodes = p.extractAllNodesThatMatch(actionFilter).toNodeArray();
        log.info("action.length = " + nodes.length);
        for (int i = 1; i < nodes.length; i++) {
            Node n = nodes[i];
            log.info("action.html = " + n.toHtml());
            if (n instanceof LinkTag) {
                String link = ((LinkTag) n).getLink();
                log.info("link = " + link);
                String text = ((LinkTag) n).getLinkText();
                log.info("text = " + text);
                list.add(text);
                list.add(link);
            }
            log.info("================");
        }
        return list;
    }

    public void parserAllNode(String url, String folder) throws IOException, MalformedURLException, ParserException {
        URLConnection connection = new URL(url).openConnection();
        Parser p = new Parser(connection);
        CssSelectorNodeFilter imgFilter = new CssSelectorNodeFilter("div[class=art_page] img");
        Node[] nodes = p.extractAllNodesThatMatch(imgFilter).toNodeArray();
        log.info("image.length = " + nodes.length);
        CountDownLatch latch = new CountDownLatch(nodes.length);
        for (int i = 0; i < nodes.length; i++) {
            Node n = nodes[i];
            log.info("n.toHtml = " + n.toHtml());
            if (n instanceof TagNode) {
                String src = ((TagNode) n).getAttribute("data-original");
                log.info("data-original = " + src);

                new Thread(new ImageParserHandler(latch, src, folder, i)).start();
            }
            log.info("======================");
        }
        try {
            latch.await();
        } catch (InterruptedException e) {
            log.error(e.getMessage());
        } finally {
            log.info("parser all node over.");
        }
    }

    private static class ImageParserHandler implements Runnable {

        private static int imageNumber = 1;
        private static final Logger log = Logger.getLogger(ImageParserHandler.class);
        private String src;
        private String folder;
        private CountDownLatch latch;
        private int id;

        public ImageParserHandler(CountDownLatch latch, String src, String folder, int i) {
            this.latch = latch;
            this.src = src;
            this.folder = folder;
            this.id = i;
        }

        @Override
        public void run() {
            try {
                saveImage(src, folder);
            } catch (Exception e) {
                log.error(e.getMessage());
            } finally {
                latch.countDown();
            }
        }

        public void saveImage(String src, String folder) {
            InputStream is = null;
            FileOutputStream os = null;
            try {
                URL url = new URL(src);
                URLConnection connection = url.openConnection();
                String contentType = connection.getContentType();
                log.info("contentType = " + contentType);
                is = connection.getInputStream();
                File file = new File(folder);
                if (!file.exists()) {
                    log.info("folder = " + folder);
                    file.mkdirs();
                }
                String name = id + ".jpg";
                os = new FileOutputStream(new File(file, name));
                byte[] bs = new byte[1024];
                for (int len = is.read(bs); len != -1; len = is.read(bs)) {
                    os.write(bs, 0, len);
                }
                os.flush();
                log.info("saved image = " + name);
            } catch (IOException e) {
                log.error(e.getMessage());
            } finally {
                if (is != null) {
                    try {
                        is.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                if (os != null) {
                    try {
                        os.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
}
