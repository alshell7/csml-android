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

import com.alshell.csmlsdk.components.Content;
import com.alshell.csmlsdk.components.ContentType;
import com.alshell.csmlsdk.components.SendingPayload;
import com.alshell.csmlsdk.components.advanced.Button;
import com.alshell.csmlsdk.components.advanced.Debug;
import com.alshell.csmlsdk.components.advanced.Question;
import com.alshell.csmlsdk.components.advanced.Typing;
import com.alshell.csmlsdk.components.advanced.Wait;
import com.alshell.csmlsdk.components.standard.Audio;
import com.alshell.csmlsdk.components.standard.File;
import com.alshell.csmlsdk.components.standard.Image;
import com.alshell.csmlsdk.components.standard.Text;
import com.alshell.csmlsdk.components.standard.URL;
import com.alshell.csmlsdk.components.standard.Video;
import com.google.gson.annotations.SerializedName;


public class Message implements SendingPayload {

    @SerializedName("payload")
    public ReceivingPayload PAYLOAD;

    @SerializedName("interaction_order")
    public Integer INTERACTION_ORDER;

    @SerializedName("conversation_id")
    public String CONVERSATION_ID;

    @SerializedName("direction")
    public String DIRECTION;

    public Message() {
    }

    @Override
    public boolean isBotSpecific() {
        return false;
    }

    @Override
    public ContentType getContentType() {
        return PAYLOAD.contentType;
    }

    @Override
    public Content getContent() {
        return PAYLOAD.content;
    }

    public Text toText() {
        return new Text(getContent().text);
    }

    public Image toImage() {
        return new Image(getContent().url);
    }

    public Audio toAudio() {
        return new Audio(getContent().url);
    }

    public Video toVideo() {
        return new Video(getContent().url);
    }

    public File toFile() {
        return new File(getContent().url);
    }

    public URL toUrl() {
        if (getContent().title != null && getContent().text != null) {
            return new URL(getContent().url, getContent().title, getContent().text);
        } else {
            return new URL(getContent().url);
        }
    }

    public Button toButton() {
        return new Button(getContent().title, getContent().payload);
    }

    public Question toQuestion() {
        return new Question(getContent().title, getContent().buttons);
    }

    public Typing toTyping() {
        return new Typing(getContent().duration);
    }

    public Wait toWait() {
        return new Wait(getContent().duration);
    }

    public Debug toDebug() {
        return new Debug(getContent().args);
    }

    public Error toError() {
        return new Error(getContent().error);
    }
}
