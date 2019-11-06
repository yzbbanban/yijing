package com.huohuo;

import com.dian.commonlib.utils.LogUtil;
import com.hd.eos.util.ec.EosPrivateKey;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() throws Exception {
        assertEquals(4, 2 + 2);
    }

    @Test
    public void createEosKey(){
        EosPrivateKey mOwnerKey = new EosPrivateKey();
        EosPrivateKey mActiveKey = new EosPrivateKey();
        LogUtil.d("test:createEosKey:mOwnerKeypub="+mOwnerKey.getPublicKey().toString());
        LogUtil.d("test:createEosKey:mOwnerKeypri="+mOwnerKey.toString());
        LogUtil.d("test:createEosKey:mActiveKeypub="+mActiveKey.getPublicKey().toString());
        LogUtil.d("test:createEosKey:mActiveKeypri="+mActiveKey.toString());
    }
}