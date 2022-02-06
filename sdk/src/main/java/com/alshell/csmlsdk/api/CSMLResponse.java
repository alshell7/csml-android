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

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class CSMLResponse {

    /**
     * A random client-issued string for tracing requests. If none is provided, will be automatically generated
     */
    @SerializedName("request_id")
    public String REQUEST_ID;

    /**
     * A random server-generated ID for the interaction
     */
    @SerializedName("interaction_id")
    public String INTERACTION_ID;

    @SerializedName("client")
    public Client CLIENT;

    /**
     * List of all the messages
     */
    @SerializedName("messages")
    public ArrayList<Message> MESSAGES = new ArrayList<>();

    /**
     * UTC time at which the message was received by the CSML server
     */
    @SerializedName("received_at")
    public String RECEIVED_AT;

    /**
     * Whether or not the user is authorized to query the chat
     */
    @SerializedName("is_authorized")
    public String IS_AUTHORIZED;

    /**
     * Tells whether if the conversation has been fulfilled by the bot.
     */
    @SerializedName("conversation_end")
    public boolean IS_CONVERSATION_END;


    public CSMLResponse() {
    }

}
