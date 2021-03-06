//  Copyright (c) Microsoft Corporation.
//  All rights reserved.
//
//  This code is licensed under the MIT License.
//
//  Permission is hereby granted, free of charge, to any person obtaining a copy
//  of this software and associated documentation files(the "Software"), to deal
//  in the Software without restriction, including without limitation the rights
//  to use, copy, modify, merge, publish, distribute, sublicense, and / or sell
//  copies of the Software, and to permit persons to whom the Software is
//  furnished to do so, subject to the following conditions :
//
//  The above copyright notice and this permission notice shall be included in
//  all copies or substantial portions of the Software.
//
//  THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
//  IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
//  FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
//  AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
//  LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
//  OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
//  THE SOFTWARE.
package com.microsoft.identity.client;

import androidx.annotation.NonNull;

import com.microsoft.identity.common.internal.authscheme.IPoPAuthenticationSchemeParams;
import com.microsoft.identity.common.internal.authscheme.PopAuthenticationSchemeInternal;

import java.net.URL;
import java.util.UUID;

public class PoPAuthenticationScheme
        extends AuthenticationScheme
        implements IPoPAuthenticationSchemeParams {

    private final URL mUrl;
    private final HttpMethod mHttpMethod;
    private final String mNonce;

    private PoPAuthenticationScheme(@NonNull final HttpMethod method,
                                    @NonNull final URL url) {
        super(PopAuthenticationSchemeInternal.SCHEME_POP);
        mHttpMethod = method;
        mUrl = url;
        mNonce = UUID.randomUUID().toString();
    }

    public static Builder builder() {
        return new Builder();
    }

    @Override
    public String getHttpMethod() {
        return mHttpMethod.name();
    }

    @Override
    public URL getUrl() {
        return mUrl;
    }

    @Override
    public String getNonce() {
        return mNonce;
    }

    public static class Builder {

        private URL mUrl;
        private HttpMethod mHttpMethod;

        private Builder() {
            // Intentionally blank
        }

        public Builder withUrl(@NonNull final URL url) {
            mUrl = url;
            return this;
        }

        public Builder withHttpMethod(@NonNull final HttpMethod method) {
            mHttpMethod = method;
            return this;
        }

        public PoPAuthenticationScheme build() {
            String errMsg = "PoP authentication scheme param must not be null: ";

            if (null == mUrl) {
                throw new IllegalArgumentException(errMsg + "URL");
            }

            if (null == mHttpMethod) {
                throw new IllegalArgumentException(errMsg + "HTTP Method");
            }

            return new PoPAuthenticationScheme(mHttpMethod, mUrl);
        }
    }
}
