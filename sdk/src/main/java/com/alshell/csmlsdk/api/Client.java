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

import androidx.annotation.NonNull;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * The user interacting with the CSML bot.
 */
public class Client {

    /**
     * The CSML Bot's ID
     */
    @Expose
    @SerializedName("bot_id")
    private String bot_id;

    /**
     * The CSML Channel's ID
     */
    @Expose
    @SerializedName("channel_id")
    private String channel_id;

    /**
     * The user's unique identifier
     */
    @Expose
    @SerializedName("user_id")
    private final String user_id;

    public Client(String user_id, String bot_id, String channel_id) {
        this.user_id = user_id;
        this.bot_id = bot_id;
        this.channel_id = channel_id;
    }

    public Client(@NonNull String user_id) {
        this.user_id = user_id;
    }

    public String getBotId() {
        return bot_id;
    }

    public String getChannelId() {
        return channel_id;
    }

    public String getUserId() {
        return user_id;
    }
}
