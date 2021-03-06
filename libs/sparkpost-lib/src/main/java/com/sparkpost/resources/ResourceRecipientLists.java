
package com.sparkpost.resources;

import com.sparkpost.exception.SparkPostException;
import com.sparkpost.model.RecipientList;
import com.sparkpost.model.responses.RecipientListRetrieveResponse;
import com.sparkpost.model.responses.RecipientListsListAllResponse;
import com.sparkpost.model.responses.Response;
import com.sparkpost.transport.RestConnection;

/**
 * Resource collection that is a 1-to-1 match to the Recipient Lists SparkPost
 * API.<br>
 * <br>
 * See
 * <a href="https://www.sparkpost.com/api#/reference/recipient-lists/">Recipient
 * Lists API</a>
 *
 * @author grava
 */
public class ResourceRecipientLists {

    public static Response create(RestConnection conn, Integer maxNumberOfRecipientErrors, RecipientList recipientList) throws SparkPostException {
        String json = recipientList.toJson();
        Endpoint ep = new Endpoint("recipient-lists");
        ep.addParam("num_rcpt_errors", maxNumberOfRecipientErrors);
        Response response = conn.post(ep.toString(), json);
        return response;
    }

    public static RecipientListRetrieveResponse retrieve(RestConnection conn, String recipientListId, Boolean showRecipients) throws SparkPostException {
        Endpoint ep = new Endpoint("recipient-lists/" + recipientListId);
        ep.addParam("show_recipients", showRecipients);
        Response response = conn.get(ep.toString());

        RecipientListRetrieveResponse retrieveResponse = RecipientListRetrieveResponse.decode(response, RecipientListRetrieveResponse.class);
        return retrieveResponse;
    }

    public static RecipientListsListAllResponse listAll(RestConnection conn) throws SparkPostException {
        Response response = conn.get("recipient-lists");
        RecipientListsListAllResponse listResponse = RecipientListsListAllResponse.decode(response, RecipientListsListAllResponse.class);
        return listResponse;
    }

    public static Response delete(RestConnection conn, String recipientListId) throws SparkPostException {
        String path = "recipient-lists/" + recipientListId;
        Response response = conn.delete(path);
        return response;
    }
}
