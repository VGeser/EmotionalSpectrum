// Generated by view binder compiler. Do not edit!
package group_project.main.emotionalspectrum.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.google.android.material.navigation.NavigationView;
import group_project.main.emotionalspectrum.R;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class NavActivityMainBinding implements ViewBinding {
  @NonNull
  private final DrawerLayout rootView;

  @NonNull
  public final ActivityMainBinding appBarDrawer;

  @NonNull
  public final DrawerLayout drawerLayout;

  @NonNull
  public final NavigationView navView;

  private NavActivityMainBinding(@NonNull DrawerLayout rootView,
      @NonNull ActivityMainBinding appBarDrawer, @NonNull DrawerLayout drawerLayout,
      @NonNull NavigationView navView) {
    this.rootView = rootView;
    this.appBarDrawer = appBarDrawer;
    this.drawerLayout = drawerLayout;
    this.navView = navView;
  }

  @Override
  @NonNull
  public DrawerLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static NavActivityMainBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static NavActivityMainBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.nav_activity_main, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static NavActivityMainBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.app_bar_drawer;
      View appBarDrawer = ViewBindings.findChildViewById(rootView, id);
      if (appBarDrawer == null) {
        break missingId;
      }
      ActivityMainBinding binding_appBarDrawer = ActivityMainBinding.bind(appBarDrawer);

      DrawerLayout drawerLayout = (DrawerLayout) rootView;

      id = R.id.nav_view;
      NavigationView navView = ViewBindings.findChildViewById(rootView, id);
      if (navView == null) {
        break missingId;
      }

      return new NavActivityMainBinding((DrawerLayout) rootView, binding_appBarDrawer, drawerLayout,
          navView);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}
