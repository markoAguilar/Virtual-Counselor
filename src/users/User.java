package users;

/**
 * A super class containing basic info for a user as instance variables
 */
public class User {
    private String _firstName;
    private String _middleName;
    private String _lastName;
    private String _gender;
    private String _email;
    private String _iD;
    private String _password;

    /**
     * determines if the user has signed in
     * @param parPassword - the password entered by the user
     * @param parID - the id entered by the user
     * @return - returns true if the user has succesfully signed in.
     */
    public boolean signIn(String parPassword, String parID){
        if(parPassword.equals(_password) & parID.equals(_iD))
            return true;
        else
            return false;

    }

    /**
     * Signs the user out
     */
    public void signOut(){
        //save all information again to the file name that matches the user's ID
    }

    /**
     * sets the first name
     * @param parfirstName - string input for first name
     */
    public void set_firstName(String parfirstName){_firstName =  parfirstName;}

    /**
     * sets the middle name
     * @param parMiddleName - string middleName to set
     */
    public void set_middleName(String parMiddleName){_middleName = parMiddleName;}

    /**
     * sets the last name
     * @param parLastNameIn - string last name to set
     */
    public void set_lastName(String parLastNameIn){_lastName = parLastNameIn;}

    /**
     * sets the gender
     * @param parGender - string gender to set
     */
    public void set_gender(String parGender){_gender = parGender;}

    /**
     * sets the email
     * @param parEmail - string email to set
     */
    public void set_email(String parEmail){_email = parEmail;}

    /**
     * sets the id
     * @param parID - String ID to set
     */
    public void set_iD(String parID){_iD = parID;}

    /**
     * sets the password
     * @param parPassword - String password to set.
     */
    public void set_Password(String parPassword){ _password = parPassword;}

    @Override
    public String toString(){
        return "Name: " + _firstName + " " + _middleName + " " + _lastName
                + "\nGender: " + _gender + "\nEmail: " + _email + "\nID: " + _iD;
    }
}
