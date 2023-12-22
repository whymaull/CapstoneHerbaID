package com.whymaull.herbaid.ui.home

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.whymaull.herbaid.databinding.FragmentHomeBinding
import com.whymaull.herbaid.utils.Classifier
import com.whymaull.herbaid.utils.getImageUri

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private var currentImageUri: Uri? = null
    private var outputLabels: List<String>? = null

    private lateinit var classifier: Classifier

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        classifier = Classifier(requireContext().assets, "model.tflite", "label.txt")
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnUpload.setOnClickListener {
            startGallery()
        }
        binding.btnTakePict.setOnClickListener {
            startCamera()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun startCamera() {
        if (ContextCompat.checkSelfPermission(requireContext(), Manifest.permission.CAMERA)
            == PackageManager.PERMISSION_GRANTED
        ) {
            currentImageUri = getImageUri(requireContext(), 224, 224, 3)
            launcherIntentCamera.launch(currentImageUri)
        } else {
            requestCameraPermission.launch(Manifest.permission.CAMERA)
        }
    }

    private val requestCameraPermission =
        registerForActivityResult(ActivityResultContracts.RequestPermission()) { isGranted ->
            if (isGranted) {
                startCamera()
            } else {
                Toast.makeText(requireContext(), "Izin kamera diperlukan.", Toast.LENGTH_SHORT).show()
            }
        }

    private val launcherIntentCamera = registerForActivityResult(
        ActivityResultContracts.TakePicture()
    ) { isSuccess ->
        if (isSuccess) {
            showImage()
            processImage()
        }
    }

    private fun processImage() {
        val preprocessedImage = preprocessImage(currentImageUri)
        val result = runInference(preprocessedImage)

        Log.d("Model Inference", "Result: $result")
        Toast.makeText(requireContext(), "Result: $result", Toast.LENGTH_SHORT).show()
    }

    private fun runInference(inputBitmap: Bitmap): String {
        val result = classifier.recognizeImage(inputBitmap)
        return result.joinToString { it.toString() }
    }

    private fun preprocessImage(uri: Uri?): Bitmap {
        val inputStream = uri?.let { requireContext().contentResolver.openInputStream(it) }
        return BitmapFactory.decodeStream(inputStream)
    }

    private fun startGallery() {
        val intent = Intent(Intent.ACTION_OPEN_DOCUMENT)
        intent.addCategory(Intent.CATEGORY_OPENABLE)
        intent.type = "image/*"
        launcherGallery.launch(intent)
    }

    private val launcherGallery = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { result: ActivityResult ->
        if (result.resultCode == android.app.Activity.RESULT_OK) {
            result.data?.data?.let { uri ->
                currentImageUri = uri
                showImage()
            }
        } else {
            Log.d("Photo Picker", "No media selected")
        }
    }

    private fun showImage() {
        currentImageUri?.let {
            Log.d("Image URI", "showImage: $it")
            binding.imgUploaded.setImageURI(it)
            processImage()
        }
    }
}
