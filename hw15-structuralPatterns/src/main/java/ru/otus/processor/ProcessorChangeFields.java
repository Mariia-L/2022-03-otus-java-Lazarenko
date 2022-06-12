package ru.otus.processor;

import ru.otus.model.Message;

public class ProcessorChangeFields implements Processor
{
    @Override
    public Message process(Message message)
    {
        String temp = message.getField11();
        message.setField11(message.getField12());
        message.setField12(temp);
        return message;
    }
}
