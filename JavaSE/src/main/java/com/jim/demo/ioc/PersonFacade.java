package com.jim.demo.ioc;

/**
 * Created with IntelliJ IDEA.
 * User: JimQiao
 * Date: 3/26/13
 * Time: 11:12 AM
 * To change this template use File | Settings | File Templates.
 */
public class PersonFacade {
    private PersonDao personDao;

    public void save(String name) {
        personDao.save(name);
    }

    public PersonDao getPersonDao() {
        return personDao;
    }

    public void setPersonDao(PersonDao personDao) {
        this.personDao = personDao;
    }
}
