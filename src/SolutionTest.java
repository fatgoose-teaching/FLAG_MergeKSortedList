import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertArrayEquals;

public class SolutionTest {
    @Test
    public void testDeleteNode() {
        // TODO: add at least one more test cases
        int[][][] inputs = {
                {
                        {1, 3, 5},
                        {-1, 2, 4, 9},
                },
                {
                        {1, 2, 3},
                        {4, 10},
                },
        };

        int[][] expects = {
                {-1, 1, 2, 3, 4, 5, 9},
                {1, 2, 3, 4, 10},
        };


        List<TestCase> testCases = getTestCases(inputs, expects);

        for (int i = 0; i < testCases.size(); i++) {
            System.out.printf("case %d\n", i);

            TestCase testCase = testCases.get(i);

            ListNode actual = Solution.mergeKLists(testCase.lists);

            assertArrayEquals(listToArray(testCase.expected), listToArray(actual));
        }
    }

    private List<TestCase> getTestCases(int[][][] inputs, int[][] outputs) {
        List<TestCase> testCases = new ArrayList<>();

        for (int i = 0; i < inputs.length; i++) {
            ListNode[] lists = new ListNode[inputs[i].length];

            for (int j = 0; j < inputs[i].length; j++) {
                lists[j] = arrayToList(inputs[i][j]);
            }

            TestCase testCase = new TestCase(lists, arrayToList(outputs[i]));
            testCases.add(testCase);
        }
        return testCases;
    }

    private ListNode arrayToList(int[] input) {
        ListNode head = null;
        ListNode ptr = null;
        for (int val : input) {
            ListNode newNode = new ListNode(val);
            if (head == null) {
                head = newNode;
                ptr = head;
                continue;
            }
            ptr.next = newNode;
            ptr = newNode;
        }
        return head;
    }

    private int[] listToArray(ListNode list) {
        List<Integer> result = new ArrayList<>();
        while (list != null) {
            result.add(list.val);
            list = list.next;
        }
        return result.stream().mapToInt(i -> i).toArray();
    }
}
