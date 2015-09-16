/**
 * Created by eoin_a on 15/09/2015.
 */

import com.example.eoin_a.im_app20.BuildConfig;
import com.example.eoin_a.im_app20.Components.DaggerappComponent;
import com.example.eoin_a.im_app20.Modules.AppModule;
import com.example.eoin_a.im_app20.MyApplication;
import com.example.eoin_a.im_app20.Utils.AppState;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricGradleTestRunner;
import org.robolectric.annotation.Config;

import javax.inject.Inject;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;


@RunWith(RobolectricGradleTestRunner.class)
@Config(constants = BuildConfig.class)
public class AppStateTest {

    AppState appstate;

    @Before
    public void setup()
    {
            appstate = new AppState(MyApplication.getInst());
    }



    @Test
    public void loggedInTest()
    {
        assertEquals(false, appstate.isLoggedIn());
        assertEquals(false, appstate.isRegistered());

        appstate.setLoggedIn(true);
        appstate.setRegistered(true);


        assertTrue(appstate.isLoggedIn());
        assertTrue(appstate.isRegistered());
        assertTrue(appstate.checkprefs());

    }







}
