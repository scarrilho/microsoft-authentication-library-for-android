package com.microsoft.identity.client.robolectric.shadows;

import com.microsoft.identity.common.internal.providers.oauth2.TokenResponse;
import com.microsoft.identity.common.internal.providers.oauth2.TokenResult;
import com.microsoft.identity.common.internal.providers.ropc.MockTestStrategy;
import com.microsoft.identity.common.internal.testutils.MockTokenResponse;

import org.robolectric.annotation.Implements;

@Implements(MockTestStrategy.class)
public class ShadowStrategyResultUnsuccessful {

    public TokenResult getTokenResult() {
        TokenResult tokenResult = new TokenResult((TokenResponse) null);
        return tokenResult;
    }

}
