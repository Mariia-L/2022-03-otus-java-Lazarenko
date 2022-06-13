package solid.processor;

public class NoBanknotesException extends Exception
{
    private final String message = "Нет подходящих банкнот";

    public String getMessage()
    {
        return message;
    }
}
