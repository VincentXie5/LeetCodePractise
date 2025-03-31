package src

func min(a, b int) int {
	if a > b {
		return b
	} else {
		return a
	}
}

func maxArea(height []int) int {
	left := 0
	right := len(height) - 1
	maxAr := 0
	for left < right {
		if maxAr < min(height[left], height[right])*(right-left) {
			maxAr = min(height[left], height[right]) * (right - left)
		}
		if height[left] < height[right] {
			left++
		} else {
			right--
		}
	}
	return maxAr
}
