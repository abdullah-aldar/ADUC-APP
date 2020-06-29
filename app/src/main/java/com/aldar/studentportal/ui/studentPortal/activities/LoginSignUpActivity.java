package com.aldar.studentportal.ui.studentPortal.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import android.os.Bundle;
import com.aldar.studentportal.R;
import com.aldar.studentportal.models.dashboardItemModels.DashboardItemModel;
import com.aldar.studentportal.ui.studentPortal.login.LoginFragment;
import com.aldar.studentportal.ui.studentPortal.studentDashboardFragments.inbox.InboxFragment;
import com.aldar.studentportal.ui.studentPortal.studentDashboardFragments.letterRequest.LetterRequestFragment;
import com.aldar.studentportal.ui.studentPortal.studentDashboardFragments.library.LibraryFragment;
import com.aldar.studentportal.ui.studentPortal.studentDashboardFragments.mainDashboardScreen.StudentDashboardFragment;
import com.aldar.studentportal.ui.studentPortal.studentDashboardFragments.myCourseAdvice.MyCourseAdviceFragment;
import com.aldar.studentportal.ui.studentPortal.studentDashboardFragments.myCourseSchedule.CourseScheduleFragment;
import com.aldar.studentportal.ui.studentPortal.studentDashboardFragments.myFinance.MyFinanceFragment;
import com.aldar.studentportal.ui.studentPortal.studentDashboardFragments.myMarks.MyMarksFragment;
import com.aldar.studentportal.ui.studentPortal.studentDashboardFragments.myProfile.StudentProfileFragment;
import com.aldar.studentportal.ui.studentPortal.studentDashboardFragments.myStudyPlan.MyStudyPlanFragment;
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
