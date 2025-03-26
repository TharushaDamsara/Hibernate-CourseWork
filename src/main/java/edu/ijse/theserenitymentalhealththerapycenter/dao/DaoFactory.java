package edu.ijse.theserenitymentalhealththerapycenter.dao;

import edu.ijse.theserenitymentalhealththerapycenter.dao.custom.impl.*;

public class DaoFactory {
    private static DaoFactory daoFactory;
    private DaoFactory() {
    }
    public static DaoFactory getInstance() {
        return daoFactory==null?daoFactory=new DaoFactory():daoFactory;
    }
    public enum daoType {
       Patient,Payment,Therapist,TherapyProgramme,TherapySession,User,Quary
    }

    public SuperDao getSuperDao(daoType daoType) {
        switch (daoType) {
          case Patient: return new PatientDaoImpl();
            case Payment:return new PaymentDaoImpl();
            case Therapist:return new TheraphistDaoImpl();
            case TherapyProgramme:return new TheraphyProgrammeDaoImpl();
            case TherapySession:return new TheraphySessonDaoImpl();
            case User:return new UserDaoImpl();
            default: return null;
        }
    }
}
