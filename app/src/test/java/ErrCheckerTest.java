import android.content.Context;

import com.example.eoin_a.im_app20.BuildConfig;
import com.example.eoin_a.im_app20.Components.DaggerappComponent;
import com.example.eoin_a.im_app20.Modules.AppModule;
import com.example.eoin_a.im_app20.MyApplication;
import com.example.eoin_a.im_app20.Utils.ErrorChecker;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricGradleTestRunner;
import org.robolectric.annotation.Config;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Created by eoin_a on 15/09/2015.
 */


@RunWith(RobolectricGradleTestRunner.class)
@Config(constants = BuildConfig.class)
public class ErrCheckerTest {


    private ErrorChecker errchecker;

    @Before
    public void setup()
    {
         Context cont = DaggerappComponent.builder().appModule(new AppModule(MyApplication.getInst())).build().cont();
         errchecker = new ErrorChecker();
         errchecker.setCont(cont);
    }



    @Test
    public void validEmailTest()
    {
       //invalid emails

        errchecker.setEmail("notvalidemail");
        assertFalse(errchecker.emailValid());
        errchecker.setEmail("jimm.***@yahoo.com.com");
        assertFalse(errchecker.emailValid());
        errchecker.setEmail(".*%&$$$$$.@fish.ie");
        assertFalse(errchecker.emailValid());
        errchecker.setEmail("");
        assertFalse(errchecker.emailValid());
        errchecker.setEmail("jimm.@yaaaaaa");
        assertFalse(errchecker.emailValid());


        // valid emails

        errchecker.setEmail("eoin@yahoo.com");
        assertTrue(errchecker.emailValid());
        errchecker.setEmail("eoin@myemail.co.uk");
        assertTrue(errchecker.emailValid());
        errchecker.setEmail("paul.murphy@mycit.ie");
        assertTrue(errchecker.emailValid());
        errchecker.setEmail("karen.collins@cpl.ie");
        assertTrue(errchecker.emailValid());
        errchecker.setEmail("orla@employabilitycork.ie");
        assertTrue(errchecker.emailValid());

    }

    @Test
    public void validPassTest()
    {

        //alphanumeric and < = 8 chars long!!

        errchecker.setPassword("pass");
        assertFalse(errchecker.passwordValid());
        errchecker.setPassword("*&C___----");
        assertFalse(errchecker.passwordValid());
        errchecker.setPassword("***@@@@@@@");
        assertFalse(errchecker.passwordValid());
        errchecker.setPassword("");
        assertFalse(errchecker.passwordValid());

        //valid passwords

        errchecker.setPassword("thisISMYPasssw0rd");
        assertTrue(errchecker.passwordValid());
        errchecker.setPassword("thug4LIFE");
        assertTrue(errchecker.passwordValid());
        errchecker.setPassword("Val1dpa22w0rd");
        assertTrue(errchecker.passwordValid());


    }

    @Test
    public void validPhoneTest()
    {


        errchecker.setPhoneNo("pass");
        assertFalse(errchecker.phonevalid());
        errchecker.setPhoneNo("*&C___----");
        assertFalse(errchecker.phonevalid());
        errchecker.setPhoneNo("+0-23---");
        assertFalse(errchecker.phonevalid());
        errchecker.setPhoneNo("");
        assertFalse(errchecker.phonevalid());


        errchecker.setPhoneNo("021436789");
        assertTrue(errchecker.phonevalid());
        errchecker.setPhoneNo("0834567832");
        assertTrue(errchecker.phonevalid());
        errchecker.setPhoneNo("0865555555");
        assertTrue(errchecker.phonevalid());
        errchecker.setPhoneNo("0879665568");
        assertTrue(errchecker.phonevalid());


    }






}
