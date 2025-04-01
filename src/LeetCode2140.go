package src

// dp algorithm
func mostPoints(questions [][]int) int64 {
	n := len(questions)
	dp := make([]int64, n)

	for r := n - 1; r >= 0; r-- {
		tmp := questions[r]
		var chooseThis int64
		if r+tmp[1]+1 >= n {
			chooseThis = int64(tmp[0])
		} else {
			chooseThis = int64(tmp[0]) + dp[r+tmp[1]+1]
		}

		var skipThis int64
		if r+1 >= n {
			skipThis = 0
		} else {
			skipThis = dp[r+1]
		}

		if chooseThis < skipThis {
			dp[r] = skipThis
		} else {
			dp[r] = chooseThis
		}
	}

	return dp[0]
}

// Recursion will time out
func mostPoints2(slice [][]int) int64 {
	if len(slice) == 0 {
		return 0
	}
	var chooseThis int64
	if len(slice) > slice[0][1]+1 {
		chooseThis = int64(slice[0][0]) + mostPoints2(slice[slice[0][1]+1:])
	} else {
		chooseThis = int64(slice[0][0])
	}

	var skipThis int64
	if len(slice) > 1 {
		skipThis = mostPoints2(slice[1:])
	} else {
		skipThis = 0
	}

	if chooseThis < skipThis {
		return skipThis
	} else {
		return chooseThis
	}
}
