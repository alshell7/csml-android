/*
 * MIT License
 *
 * Copyright (c) 2022 alshell7
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package com.alshell.csmlsdk.api;

import com.alshell.csmlsdk.components.SendingPayload;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class CSMLRequest {

    /**
     * The request to bot sent on behalf of.
     */
    @Expose
    @SerializedName("client")
    private final Client client;

    /**
     * Key-value pairs of metadata to inject into the conversation
     */
    @Expose
    @SerializedName("metadata")
    private Map<String, String> metaData = new HashMap<String, String>();
    ;

    @Expose
    @SerializedName("request_id")
    private final String requestId;

    @Expose
    @SerializedName("payload")
    private final SendingPayload payload;

    public CSMLRequest(Client client, SendingPayload payload) {
        this.client = client;
        this.requestId = UUID.randomUUID().toString();
        this.payload = payload;
    }

    public CSMLRequest(Client client, Map<String, String> metaData, SendingPayload payload) {
        this.client = client;
        this.requestId = UUID.randomUUID().toString();
        this.payload = payload;
        this.metaData = metaData;
    }

//    public CSMLRequest(Client client, Map<String, String> metaData, String requestId, Payload payload) {
//        this.client = client;
//        this.requestId = requestId;
//        this.payload = payload;
//        this.metaData = metaData;
//    }
}
