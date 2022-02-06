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

package com.alshell.csmlsdk.components;

import androidx.annotation.NonNull;

import com.google.gson.annotations.Expose;

import java.util.ArrayList;

public class Content {

    public Content() {
    }

    /**
     * Used by:
     * Ref: https://docs.csml.dev/language/sending-receiving-messages/message-payloads#text*
     * Ref:https://docs.csml.dev/language/sending-receiving-messages/message-payloads#url
     */
    public String text = null;

    /**
     * Used by:
     * Ref: https://docs.csml.dev/language/sending-receiving-messages/message-payloads#typing
     * Ref: https://docs.csml.dev/language/sending-receiving-messages/message-payloads#wait
     */
    @Expose
    public Integer duration = null;


    /**
     * Used by:
     * Ref: https://docs.csml.dev/language/sending-receiving-messages/message-payloads#url
     * Ref: https://docs.csml.dev/language/sending-receiving-messages/message-payloads#image
     * Ref: https://docs.csml.dev/language/sending-receiving-messages/message-payloads#audio
     * Ref: https://docs.csml.dev/language/sending-receiving-messages/message-payloads#video
     * Ref: https://docs.csml.dev/language/sending-receiving-messages/message-payloads#file
     */
    public String url = null;

    /**
     * Used by:
     * Ref: https://docs.csml.dev/language/sending-receiving-messages/message-payloads#url
     * Ref: https://docs.csml.dev/language/sending-receiving-messages/message-payloads#button
     * Ref: https://docs.csml.dev/language/sending-receiving-messages/message-payloads#question
     */
    public String title = null;

    /**
     * Used by:
     * Ref: https://docs.csml.dev/language/sending-receiving-messages/message-payloads#button
     * Ref: https://docs.csml.dev/language/sending-receiving-messages/message-payloads#payload
     */
    public String payload = null;

    /**
     * Used by:
     * Ref: https://docs.csml.dev/studio/api/api-reference/chat-api#triggering-a-specific-flow
     */
    public String flow_id = null; // Trigger Flow

    @Expose
    public Boolean close_flows = null; // Force-close any previously open conversation with close_flows

    /**
     * Used by:
     * Ref: https://docs.csml.dev/language/sending-receiving-messages/message-payloads#question
     */
    public ArrayList<ButtonContent> buttons = null;


    /*
     * Raised by error type
     * */
    public String error = null;

    /*
     * Given by debug type
     * */
    public ArrayList<String> args = null;


    @NonNull
    @Override
    public String toString() {
        return "Content{" +
                "text='" + text + '\'' +
                ", duration=" + duration +
                ", url='" + url + '\'' +
                ", title='" + title + '\'' +
                ", payload='" + payload + '\'' +
                ", flow_id='" + flow_id + '\'' +
                ", close_flows=" + close_flows +
                '}';
    }
}
