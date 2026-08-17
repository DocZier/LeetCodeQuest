package ArrayI.Q3

import kotlin.math.max

//Max Consecutive Number

//Main idea - create a window and store window length. Then increment length and when first window ends search for the next.
// Bad solution. Very bad.
class Solution {
    fun findMaxConsecutiveOnes(nums: IntArray): Int {
        var windowLength = 0
        var localWindowLength = 0
        var i = 1

        if (nums.size > 2) {
            while (i < nums.size) {
                if (nums[i - 1] > 0) {
                    if (i - 1 + windowLength < nums.size) {
                        if ((nums[i - 1 + windowLength] > 0)) {
                            localWindowLength = 0
                            for (j in i - 1..<nums.size) {
                                if (nums[j] > 0) {
                                    localWindowLength += 1
                                    println("Found 1 in ${j} position. Local window length is $localWindowLength")
                                } else {
                                    println("Found break point in ${j} position")
                                    break
                                }
                            }
                            if (windowLength < localWindowLength) windowLength = localWindowLength
                            i+=localWindowLength-1
                        } else {
                            println(
                                "Skipping from ${i} position to ${i + windowLength} position. 1C: ${
                                    i - 1 + max(
                                        1,
                                        windowLength
                                    ) < nums.size
                                }. 2C: ${nums[i - 1 + windowLength] == 1}"
                            )
                            i+=windowLength
                        }
                    } else{
                        println("Out of bounds: index: ${i}, window length: ${windowLength}")
                        break
                    }
                }
                i++
            }
            return windowLength
        } else return nums.sum()
    }
}
//Best solution. Simple and fast
class Solution2 {
    fun findMaxConsecutiveOnes(nums: IntArray): Int {
        var maxCount = 0
        var localCount = 0
        for (n in nums) {
            if (n == 1) localCount++
            else {
                maxCount = max(maxCount, localCount)
                localCount = 0
            }
        }
        return if (maxCount > localCount) maxCount else localCount
    }
}