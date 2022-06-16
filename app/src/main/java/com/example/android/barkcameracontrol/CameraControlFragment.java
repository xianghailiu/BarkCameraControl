package com.example.android.barkcameracontrol;

import android.app.admin.DevicePolicyManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.Switch;

import com.example.android.barkcameracontrol.databinding.FragmentCameraControlBinding;
import com.example.android.barkcameracontrol.util.Utils;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link CameraControlFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CameraControlFragment extends Fragment {
    private static final String TAG = CameraControlFragment.class.getSimpleName();

    static CameraControlFragment cameraControlFragment;
    private FragmentCameraControlBinding binding;
    public static final int DPM_ACTIVATION_REQUEST_CODE = 100;

    private ComponentName adminComponent;
    private DevicePolicyManager devicePolicyManager;

    public CameraControlFragment() {
        // Required empty public constructor
    }

    public static Fragment newInstance() {
        if(cameraControlFragment == null) {
            cameraControlFragment = new CameraControlFragment();
        }
        return cameraControlFragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentCameraControlBinding.inflate(inflater, container, false);

        devicePolicyManager = (DevicePolicyManager) getActivity().getSystemService(Context.DEVICE_POLICY_SERVICE);
        adminComponent = new ComponentName(getActivity().getPackageName(),getActivity().getPackageName() + ".DeviceAdministrator");

        // Request device admin activation if not enabled.
        if (!devicePolicyManager.isAdminActive(adminComponent)) {
            Intent activateDeviceAdmin = new Intent(DevicePolicyManager.ACTION_ADD_DEVICE_ADMIN);
            activateDeviceAdmin.putExtra(DevicePolicyManager.EXTRA_DEVICE_ADMIN, adminComponent);
            startActivityForResult(activateDeviceAdmin, DPM_ACTIVATION_REQUEST_CODE);
        }

        // Check admin component, init switch status
        if (devicePolicyManager.getCameraDisabled(adminComponent)) {
            binding.cameraSwitch.setChecked(false);
        } else {
            binding.cameraSwitch.setChecked(true);
        }

        //Set switch check listener
        binding.cameraSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isEnabled) {
                try {
                    if (isEnabled) {
                        Utils.showToastShort("Enable camera");
                        devicePolicyManager.setCameraDisabled(adminComponent, false); // Enable camera.
                    } else {
                        Utils.showToastShort("Disable camera");
                        devicePolicyManager.setCameraDisabled(adminComponent, true); // Disable camera.
                    }
                } catch (SecurityException securityException) {
                    Log.e(TAG, "Error occurred while disabling/enabling camera - " + securityException.getMessage());
                }
            }
        });

//        if (devicePolicyManager.getCameraDisabled(adminComponent)) {
//            binding.cameraSwitch.setChecked(false);
//        } else {
//            binding.cameraSwitch.setChecked(true);
//        }

        return binding.getRoot();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode== DPM_ACTIVATION_REQUEST_CODE) {
            //do nothing for now
        }
    }
}