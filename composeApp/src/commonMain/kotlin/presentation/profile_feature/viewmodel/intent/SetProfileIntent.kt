package presentation.profile_feature.viewmodel.intent

import androidx.compose.ui.graphics.ImageBitmap
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.launch
import org.example.project.core.StaticDate
import org.example.project.presentation.profile_feature.viewmodel.ProfileViewModel


object SetProfileIntent {
    fun execute(images:MutableList<ImageBitmap>){
    ProfileViewModel(StaticDate).date.photo = images[0]
    }
}
