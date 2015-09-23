/**
 * Created by eoin_a on 18/09/2015.
 */

import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.eoin_a.im_app20.BuildConfig;
import com.example.eoin_a.im_app20.R;
import com.example.eoin_a.im_app20.Views.RegistrationActivity;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricGradleTestRunner;
import org.robolectric.annotation.Config;

import static junit.framework.Assert.assertNotNull;


@RunWith(RobolectricGradleTestRunner.class)
@Config(constants = BuildConfig.class)
public class RegActivityTest {

    RegistrationActivity regactivity;
    private TextView tv1;
    private TextView tv2;
    private TextView tv3;
    private Button button;
    private EditText ed1;
    private EditText ed2;
    private EditText ed3;

    @Before
    public void setup()
    {
        regactivity = Robolectric.buildActivity(RegistrationActivity.class).create().get();

        tv1 = (TextView) regactivity.findViewById(R.id.tv1);
        tv2 = (TextView) regactivity.findViewById(R.id.tv2);
        tv3 = (TextView) regactivity.findViewById(R.id.tv3);
        ed1= (EditText) regactivity.findViewById(R.id.edtxtemail);
        ed2 = (EditText) regactivity.findViewById(R.id.edtxtpass);
        ed3 = (EditText) regactivity.findViewById(R.id.edtxtphone);
        button = (Button) regactivity.findViewById(R.id.button);

    }


    @Test
    public void notNullTest()
    {
        assertNotNull(regactivity);

        assertNotNull(tv1);
        assertNotNull(tv2);
        assertNotNull(tv3);
        assertNotNull(ed2);
        assertNotNull(ed3);
        assertNotNull(ed1);
        assertNotNull(button);

    }

}
