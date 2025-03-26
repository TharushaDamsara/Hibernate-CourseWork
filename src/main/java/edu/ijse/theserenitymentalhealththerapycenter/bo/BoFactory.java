package edu.ijse.theserenitymentalhealththerapycenter.bo;


import edu.ijse.theserenitymentalhealththerapycenter.bo.custom.impl.*;

public class BoFactory {

    private static BoFactory factory;
    private BoFactory() {}
    public static BoFactory getInstance() {
        return factory==null?factory=new BoFactory():factory;
    }
    public enum botype {
        Patient,Payment,Therapist,TherapyProgramme,TherapySession,User,Quary
    }
    public SuperBo getboType(botype type) {
        switch (type) {
            case Patient:return new PatientBoImpl();
            case Payment:return new PaymentBoImpl();
            case Therapist:return new TheraphistBoImpl();
            case TherapyProgramme:return new TheraphyProgrammeBoImpl();
            case TherapySession:return new TheraphySessonBoImpl();
            case User:return new UserBoImpl();
            default:return null;
        }
    }
}
