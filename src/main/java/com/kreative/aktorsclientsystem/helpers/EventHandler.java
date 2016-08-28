/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kreative.aktorsclientsystem.helpers;

import static com.kreative.aktorsclientsystem.helpers.WebSocketConfiguration.MESSAGE_PREFIX;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.core.annotation.HandleAfterCreate;
import org.springframework.data.rest.core.annotation.HandleAfterDelete;
import org.springframework.data.rest.core.annotation.HandleAfterSave;
import org.springframework.data.rest.core.annotation.RepositoryEventHandler;
import org.springframework.hateoas.EntityLinks;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;
import com.kreative.aktorsclientsystem.models.User;

/**
 *
 * @author Ahmed
 */
@Component
@RepositoryEventHandler(User.class)
public class EventHandler {

    private final SimpMessagingTemplate websocket;
    private final EntityLinks entityLinks;

    @Autowired
    public EventHandler(SimpMessagingTemplate websocket, EntityLinks entityLinks) {
        this.websocket = websocket;
        this.entityLinks = entityLinks;
    }

    @HandleAfterCreate
    public void newUser(User client) {
        this.websocket.convertAndSend(
                MESSAGE_PREFIX + "/newUser", getPath(client));
    }

    @HandleAfterDelete
    public void deleteUser(User client) {
        this.websocket.convertAndSend(
                MESSAGE_PREFIX + "/deleteUser", getPath(client));
    }

    @HandleAfterSave
    public void updateUser(User client) {
        this.websocket.convertAndSend(
                MESSAGE_PREFIX + "/updateUser", getPath(client));
    }

    private String getPath(User client) {
        return this.entityLinks.linkForSingleResource(client.getClass(),
                client.getId()).toUri().getPath();
    }

}
