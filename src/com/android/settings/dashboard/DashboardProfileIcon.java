package com.android.settings.dashboard;

import android.content.Context;
import android.os.UserManager;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.content.pm.UserInfo;

import com.android.settings.R;
import com.android.settingslib.Utils;
import com.android.settings.core.SubSettingLauncher;
import com.android.settings.users.UserSettings;

import com.android.internal.logging.nano.MetricsProto;

public class DashboardProfileIcon extends LinearLayout implements OnClickListener {

    private View mUsers;

    public DashboardProfileIcon(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }
        public DashboardProfileIcon(Context context) {
        super(context);
        init(context);
    }

    private void init(Context context) {
        View view = inflate(context, R.layout.item_dashboard_profile, this);
        ImageView icon = view.findViewById(R.id.user_icon);
        view.setClipToOutline(true);
        icon.setClipToOutline(true);
        UserManager userManager = (UserManager) context.getSystemService(Context.USER_SERVICE);
        UserInfo info = com.android.settings.Utils.getExistingUser(userManager, android.os.Process.myUserHandle());
        icon.setImageDrawable(Utils.getUserIcon(context, userManager, info));
        mUsers = findViewById(R.id.dashboard_profile_icon);
        mUsers.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v == mUsers) {
            new SubSettingLauncher(getContext())
                    .setDestination(UserSettings.class.getName())
                    .setSourceMetricsCategory(MetricsProto.MetricsEvent.CANDYSHOP)
                    .launch();
        }
    }
}
