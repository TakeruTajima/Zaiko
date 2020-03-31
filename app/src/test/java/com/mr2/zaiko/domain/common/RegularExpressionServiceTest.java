package com.mr2.zaiko.domain.common;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class RegularExpressionServiceTest {

    @Before
    public void setUp() throws Exception {
    }

    @Test
    public void zenkakuToHankaku() {
        assertEquals("AaBbCcDdEeFfGgHhIiJjKkLlMmNnOoPpQqRrSsTtUuVvWwXxYyZz1234567890",
                RegularExpressionService.zenkakuToHankaku(
                        "ＡａＢｂＣｃＤｄＥｅＦｆＧｇＨｈＩｉＪｊＫｋＬｌＭｍＮｎＯｏＰｐＱｑ" +
                                "ＲｒＳｓＴｔＵｕＶｖＷｗＸｘＹｙＺｚ１２３４５６７８９０"));
    }
}