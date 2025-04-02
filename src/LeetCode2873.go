package src

func maximumTripletValue(nums []int) int64 {
	n := len(nums)
	Left := make([]int64, n)
	Right := make([]int64, n)
	Left[0] = int64(nums[0])
	Right[n-1] = int64(nums[n-1])
	for i := 1; i < n; i++ {
		Left[i] = max(Left[i-1], int64(nums[i]))
		Right[n-i-1] = max(Right[n-i], int64(nums[n-i-1]))
	}
	answer := int64(0)
	for i := 1; i < n-1; i++ {
		answer = max(answer, (Left[i-1]-int64(nums[i]))*Right[i+1])
	}
	return answer
}

func max(i, j int64) int64 {
	if i > j {
		return i
	} else {
		return j
	}
}
