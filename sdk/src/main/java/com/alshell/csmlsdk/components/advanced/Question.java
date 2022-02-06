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

package com.alshell.csmlsdk.components.advanced;

import androidx.annotation.NonNull;

import com.alshell.csmlsdk.components.ButtonContent;
import com.alshell.csmlsdk.components.Content;
import com.alshell.csmlsdk.components.ContentType;
import com.alshell.csmlsdk.components.SendingPayload;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class Question implements SendingPayload {
    @Expose
    @SerializedName("content_type")
    private final ContentType contentType;

    @Expose
    @SerializedName("content")
    private final Content content;

    public Question(@NonNull String title, @NonNull ArrayList<ButtonContent> buttons) {
        this.contentType = ContentType.question;
        this.content = new Content();
        this.content.title = title;
        this.content.buttons = buttons;
    }

    @Override
    public boolean isBotSpecific() {
        return true;
    }

    @Override
    public ContentType getContentType() {
        return contentType;
    }

    /**
     * @return Gets the title of the Question.
     */
    public String getTitle() {
        return this.content.title;
    }


    /**
     * @return Gets list of buttons
     */
    public ArrayList<ButtonContent> getButtons() {
        return this.content.buttons;
    }

    @Override
    public Content getContent() {
        return content;
    }
}
