package edu.ijse.theserenitymentalhealththerapycenter.util.exceptionsPack;

public class CustomEXception extends Exception{
    public CustomEXception(String message){
        super(message);
    }

    public static void IsNull(Object obj) throws CustomEXception {
        /*
        * HERE GET THE OBJECT INPUT AND AFTER THAT CHECK THAT IS NULL
        * IS IT NULL THERE WE GIVE THE NULL POINT EXCEPTION...
        * */
        if(obj == null || obj.equals("")){
            throw new CustomEXception("NULL POINT EXCEPTION");
        }
    }
}
