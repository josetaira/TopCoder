import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

/**
 * Source: Topcoder - TCCC '04 Finals
 * Category: Hard
 * Points: 1200
 * Passes System Test: true
 * @author Jose Taira
 */
public class DataFilter {

    private static Pattern idPattern = Pattern.compile("^0[0-9]*$");
    private static Pattern agePattern = Pattern.compile("^[1-9][0-9]{0,2}$");

    public static final String RECORD_DELIMITER = ";";
    public static final String INFO_DELIMITER = " ";

    /**
     * @param data
     * @return count of unique employees
     */
    public int untangle(String[] data) {
        Map<String, Employee> idMap = new HashMap<>();
        List<Employee> emps = parseUniqueUnmergedEmployees(data);

        if(emps == null) {
            return -1;
        }

        return mergeEmployees(emps).size();
    }

    /**
     * Parses the data provided by splitting individual elements by semicolon, then by space.
     * Each info is placed into the employee it belongs to, or creates a new potential employee.
     *
     * @param data
     * @return List of unmerged employees if there are no conflict on data, null otherwise
     */
    private List<Employee> parseUniqueUnmergedEmployees(String[] data) {
        List<Employee> emps = new ArrayList<>();
        for(int i = 0; i < data.length; i++) {
            String[] splits = data[i].split(RECORD_DELIMITER);
            for(int j = 0; j < splits.length; j++) {
                String[] info = splits[j].split(INFO_DELIMITER);

                String name = null;
                String id = null;
                String age = null;

                for(int k = 0; k < info.length; k++) {
                    if(idPattern.matcher(info[k]).matches()) {
                        id = info[k];
                    } else if(agePattern.matcher(info[k]).matches()) {
                        age = info[k];
                    } else {
                        name = info[k];
                    }
                }

                int empsSize = emps.size();
                boolean found = false;

                if(id != null) {
                    for(int idx = 0; idx < empsSize; idx++) {
                        Employee currEmp = emps.get(idx);
                        if(id.equals(currEmp.id)) {
                            if (currEmp.age == null) {
                                currEmp.age = age;
                            } else if (age != null && !currEmp.age.equals(age)) {
                                return null;
                            }

                            if (currEmp.name == null) {
                                currEmp.name = name;
                            } else if (name != null && !currEmp.name.equals(name)) {
                                return null;
                            }

                            found = true;
                            break;
                        }
                    }
                } else if(name != null) {
                    for (int idx = 0; idx < empsSize; idx++) {
                        Employee currEmp = emps.get(idx);
                        if (name.equals(currEmp.name)) {
                            if (age == null || age.equals(currEmp.age)) {
                                found = true;
                                break;
                            }
                        }
                    }
                } else if(age != null) {
                    for(int idx = 0; idx < empsSize; idx++) {
                        Employee currEmp = emps.get(idx);
                        if(age.equals(currEmp.age)) {
                            found = true;
                            break;
                        }
                    }
                }

                if(!found) {
                    emps.add(new Employee(id, name, age));
                }
            }
        }

        return emps;
    }

    /**
     * Summary:
     * 1. Get the total number of possible merges. If no possible merges
     *    or no more unprocessed records, we're done.
     * 2. Get employee record (merge source) with the least number of possible merges.
     * 3. If the merge source has no potential merges, remove it from the unprocessed list
     *    and add it to the processed list. Repeat #1
     * 4. For each record it can merge with (merge target), check the resulting total
     *    number of merges when, excluding merge source and merge target. Get the merge
     *    target that results in the smallest value.
     * 5. Merge merge source and merge target
     * 6. Remove merge target from the unprocessed list; add merge source back to the unprocessed list
     * 7. Repeat #1
     * @param emps
     * @return
     */
    private List<Employee> mergeEmployees(List<Employee> emps) {
        List<Employee> mergedEmps = new ArrayList<>();

        while(emps.size() > 0) {
            Employee currEmp = null;

            int oldPossibleMerges = 0;
            int currSize = emps.size();

            int minCountMerges = Integer.MAX_VALUE;
            int minMergeIdx = -1;
            // Count the total possible moves before merging
            // Also find the index with smallest possible moves
            for(int i = 0; i < currSize; i++) {
                Employee tempCurrEmp = emps.get(i);
                int currCountMerges = 0;
                for(int j = 0; j < currSize; j++) {
                    if(i != j && tempCurrEmp.canMergeWith(emps.get(j))) {
                        currCountMerges++;
                    }
                }
                if(currCountMerges < minCountMerges) {
                    minCountMerges = currCountMerges;
                    minMergeIdx = i;
                }
                oldPossibleMerges += currCountMerges;
            }

            if(minMergeIdx == -1) {
                mergedEmps.addAll(emps);
                emps.removeAll(mergedEmps);
                continue;
            }
            currEmp = emps.remove(minMergeIdx);

            currSize = emps.size();

            List<Integer> mergeables = new ArrayList<>();
            for(int i = 0; i < currSize; i++) {
                if(currEmp.canMergeWith(emps.get(i))) {
                    mergeables.add(i);
                }
            }

            int mergePossibilities = mergeables.size();
            if(mergePossibilities == 0) {
                mergedEmps.add(currEmp);
                continue;
            }

            int leastChangeAmount = Integer.MAX_VALUE;
            int leastChangeIdx = -1;
            for(int k = 0; k < mergeables.size(); k++) {
                int currIdx = mergeables.get(k);
                int currentPossibleMoves = 0;
                for (int i = 0; i < currSize; i++) {
                    if(currIdx == i) {
                        continue;
                    }
                    Employee tempCurrEmp = emps.get(i);
                    for (int j = 0; j < currSize; j++) {
                        if(i == j || currIdx == j) {
                            continue;
                        }

                        if (tempCurrEmp.canMergeWith(emps.get(j))) {
                            currentPossibleMoves++;
                        }
                    }
                }

                if(oldPossibleMerges - currentPossibleMoves < leastChangeAmount) {
                    leastChangeAmount = oldPossibleMerges - currentPossibleMoves;
                    leastChangeIdx = currIdx;
                }
            }

            currEmp.merge(emps.remove(leastChangeIdx));
            emps.add(currEmp);

        }

        return mergedEmps;
    }

    public class Employee {
        public String name;
        public String age;
        public String id;

        public Employee(String id, String name, String age) {
            this.id = id;
            this.name = name;
            this.age = age;
        }

        public void merge(Employee e) {
            if(this.id == null) {
                this.id = e.id;
            }

            if(this.name == null) {
                this.name = e.name;
            }

            if(this.age == null) {
                this.age = e.age;
            }
        }

        public boolean canMergeWith(Employee e) {
            if(this.id != null && e.id != null && !this.id.equals(e.id)) {
                return false;
            }

            if(this.name != null && e.name != null && !this.name.equals(e.name)) {
                return false;
            }

            if(this.age != null && e.age != null && !this.age.equals(e.age)) {
                return false;
            }

            return true;
        }

        @Override
        public String toString() {
            return String.format("%s %s %s", id, name, age);
        }
    }

}
