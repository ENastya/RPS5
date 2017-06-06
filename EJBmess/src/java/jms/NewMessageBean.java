/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jms;

import javax.annotation.Resource;
import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.inject.Inject;
import javax.jms.JMSConnectionFactory;
import javax.jms.JMSContext;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.Queue;

/**
 *
 * @author Настя
 */
@MessageDriven(activationConfig = {
    @ActivationConfigProperty(propertyName = "destinationLookup", propertyValue = "amqmsg"),
    @ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Queue")
})
public class NewMessageBean implements MessageListener {

    @Resource(mappedName = "test")
    private Queue amqmsg;

    @Inject
    @JMSConnectionFactory("amqpool")
    private JMSContext context;
    
    public NewMessageBean() {
    }
    
    @Override
    public void onMessage(Message message) {
    }

    private void sendJMSMessageToAmqmsg(String messageData) {
        context.createProducer().send(amqmsg, messageData);
    }
    
}
