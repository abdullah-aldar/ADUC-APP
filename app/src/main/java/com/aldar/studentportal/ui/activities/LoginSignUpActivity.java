package com.aldar.studentportal.ui.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import android.os.Bundle;
import com.aldar.studentportal.R;
import com.aldar.studentportal.models.dashboardItemModels.DashboardItemModel;
import com.aldar.studentportal.ui.login.LoginFragment;
import com.aldar.studentportal.ui.studentPortal.addDrop.AddDropFragment;
import com.aldar.studentportal.ui.studentPortal.gradeConversion.GradeConversionFragment;
import com.aldar.studentportal.ui.studentPortal.inbox.InboxFragment;
import com.aldar.studentportal.ui.studentPortal.letterRequest.LetterRequestFragment;
import com.aldar.studentportal.ui.studentPortal.library.LibraryFragment;
import com.aldar.studentportal.ui.studentPortal.mainDashboardScreen.StudentDashboardFragment;
import com.aldar.studentportal.ui.studentPortal.myCourseAdvice.MyCourseAdviceFragment;
import com.aldar.studentportal.ui.studentPortal.myCourseSchedule.CourseScheduleFragment;
import com.aldar.studentportal.ui.studentPortal.myFinance.MyFinanceFragment;
import com.aldar.studentportal.ui.studentPortal.myMarks.MyMarksFragment;
import com.aldar.studentportal.ui.studentPortal.myProfile.StudentProfileFragment;
import com.aldar.studentportal.ui.studentPortal.myStudyPlan.MyStudyPlanFragment;
import com.aldar.studentportal.utilities.OtherUtils;
import com.aldar.studentportal.utilities.SharedPreferencesManager;

public class LoginSignUpActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        boolean isLogin = SharedPreferencesManager.getInstance(getApplicationContext()).getBooleanValue("isLogin");
        if(!isLogin){
            loadFragmentWithoutBack(new LoginFragment());
        }
        else {
            loadFragmentWithoutBack(new StudentDashboardFragment());
        }
    }

    public void show(DashboardItemModel item) {
        switch (item.getId()) {
            case 0:
                loadFragment(new CourseScheduleFragment());
                break;
            case 1:
                loadFragment(new MyMarksFragment());
                break;
            case 2:
                loadFragment(new MyStudyPlanFragment());
                break;
            case 3:
                loadFragment(new MyCourseAdviceFragment());
                break;
            case 4:
                loadFragment(new LetterRequestFragment());
                break;
            case 5:
                loadFragment(new MyFinanceFragment());
                break;
            case 6:
                loadFragment(new StudentProfileFragment());
                break;
            case 7:
                loadFragment(new InboxFragment());
                break;
            case 8:
                OtherUtils.googleClassRoom(getApplicationContext());
                break;
            case 9:
                loadFragment(new LibraryFragment());
                break;
            case 10:
                loadFragment(new GradeConversionFragment());
                break;
            case 11:
                loadFragment(new AddDropFragment());
                break;
        }
    }


    private void loadFragment(Fragment fragment){
        getSupportFragmentManager()
                .beginTransaction()
                .addToBackStack("BACK")
                .setCustomAnimations(android.R.anim.slide_in_left,android.R.anim.slide_out_right )
                .replace(R.id.fragment_container,
                        fragment, null).commit();
    }



    private void loadFragmentWithoutBack(Fragment fragment){
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragment_container,
                        fragment, null).commit();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
