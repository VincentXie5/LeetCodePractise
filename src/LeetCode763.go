package src

func partitionLabels(s string) []int {
	lastChPos := make(map[rune]int)

	for i, ch := range s {
		lastChPos[ch] = i
	}

	var result []int
	start := 0
	end := 0

	for i, ch := range s {
		if lastChPos[ch] > end {
			end = lastChPos[ch]
		}
		if i == end {
			result = append(result, end-start+1)
			start = end + 1
		}
	}
	return result
}
