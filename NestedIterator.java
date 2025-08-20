//Time Complexity: Amortized O(1) per next() and hasNext() call (each element visited once over time).
//Space Complexity: O(D) in the worst case, where D is the maximum depth of nesting due to stack usage.

//Keep two stacks: one for the current list frames and one for their indices; initialize with the top-level list and index 0.
//In hasNext(), loop: if current index == list size, pop; otherwise fetch element at (list, index) and increment index.
//If it’s an integer, cache it in nextEl and return true; if it’s a list, push that list with index 0 and continue; next() simply returns nextEl.getInteger().

/**
 * // This is the interface that allows for creating nested lists.
 * // You should not implement it, or speculate about its implementation
 * public interface NestedInteger {
 *
 *     // @return true if this NestedInteger holds a single integer, rather than a nested list.
 *     public boolean isInteger();
 *
 *     // @return the single integer that this NestedInteger holds, if it holds a single integer
 *     // Return null if this NestedInteger holds a nested list
 *     public Integer getInteger();
 *
 *     // @return the nested list that this NestedInteger holds, if it holds a nested list
 *     // Return empty list if this NestedInteger holds a single integer
 *     public List<NestedInteger> getList();
 * }
 */
public class NestedIterator implements Iterator<Integer> {
    Stack<List<NestedInteger>> st ;
    Stack<Integer> idxSt ;
    NestedInteger nextEl;

    public NestedIterator(List<NestedInteger> nestedList) {
        this.st = new Stack<>();
        this.idxSt = new Stack<>();

        st.push(nestedList);
        idxSt.push(0);
    }

    @Override
    public Integer next() {
        return nextEl.getInteger();
    }

    @Override
    public boolean hasNext() {
        while(!st.isEmpty()){
            if(st.peek().size() == idxSt.peek()){
                st.pop();
                idxSt.pop();
            } else {
                List<NestedInteger> currentList = st.peek();
                int currentIdx = idxSt.pop();
                idxSt.push(currentIdx + 1);
                nextEl = currentList.get(currentIdx);
                if(nextEl.isInteger()){
                    return true;
                } else {
                    st.push(nextEl.getList());
                    idxSt.push(0);
                }
            }
        }
        return false;
    }
}

/**
 * Your NestedIterator object will be instantiated and called as such:
 * NestedIterator i = new NestedIterator(nestedList);
 * while (i.hasNext()) v[f()] = i.next();
 */
