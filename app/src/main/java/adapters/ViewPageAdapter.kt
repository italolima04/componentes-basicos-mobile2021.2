package adapters

import androidx.annotation.NonNull
import androidx.fragment.app.Fragment

import androidx.fragment.app.FragmentActivity

import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.componentesbsicos.MainActivity
import com.example.componentesbsicos.PageOne
import com.example.componentesbsicos.PageTwo


class ViewPagerAdapter(fragmentActivity: FragmentActivity) :
    FragmentStateAdapter(fragmentActivity) {
    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> PageOne()
            1 -> PageTwo()
            else -> PageTwo()
        }
    }

    override fun getItemCount(): Int {
        return 2
    }
}