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

package com.alshell.csmlsdk.components.standard;

import androidx.annotation.NonNull;

import com.alshell.csmlsdk.components.Content;
import com.alshell.csmlsdk.components.ContentType;
import com.alshell.csmlsdk.components.SendingPayload;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Video implements SendingPayload {
    @Expose
    @SerializedName("content_type")
    private final ContentType contentType;

    @Expose
    @SerializedName("content")
    private final Content content;

    public Video(@NonNull String url) {
        this.contentType = ContentType.video;
        this.content = new Content();
        this.content.url = url;
    }

    /**
     * @return Gets the URL of the Video.
     */
    public String getUrl() {
        return this.content.url;
    }

    @Override
    public boolean isBotSpecific() {
        return false;
    }

    @Override
    public ContentType getContentType() {
        return contentType;
    }

    @Override
    public Content getContent() {
        return content;
    }
}
