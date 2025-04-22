import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.*;

class Policy implements Comparable<Policy> {
    String policyNumber;
    String policyholderName;
    LocalDate expiryDate;
    String coverageType;
    double premiumAmount;

    public Policy(String policyNumber, String policyholderName, LocalDate expiryDate, String coverageType, double premiumAmount) {
        this.policyNumber = policyNumber;
        this.policyholderName = policyholderName;
        this.expiryDate = expiryDate;
        this.coverageType = coverageType;
        this.premiumAmount = premiumAmount;
    }

    public boolean isExpiringSoon() {
        return ChronoUnit.DAYS.between(LocalDate.now(), expiryDate) <= 30;
    }

    public String toString() {
        return policyNumber + " - " + policyholderName + " - " + expiryDate + " - " + coverageType + " - " + premiumAmount;
    }

    public int compareTo(Policy other) {
        return this.expiryDate.compareTo(other.expiryDate);
    }

    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Policy)) return false;
        Policy p = (Policy) o;
        return policyNumber.equals(p.policyNumber);
    }

    public int hashCode() {
        return policyNumber.hashCode();
    }
}

public class PolicyManager {
    Set<Policy> hashSet = new HashSet<>();
    Set<Policy> linkedHashSet = new LinkedHashSet<>();
    Set<Policy> treeSet = new TreeSet<>();

    public void addPolicy(Policy policy) {
        hashSet.add(policy);
        linkedHashSet.add(policy);
        treeSet.add(policy);
    }

    public void showAllPolicies(Set<Policy> set) {
        for (Policy policy : set) {
            System.out.println(policy);
        }
    }

    public void showExpiringSoon() {
        for (Policy p : treeSet) {
            if (p.isExpiringSoon()) {
                System.out.println(p);
            }
        }
    }

    public void showByCoverageType(String type) {
        for (Policy p : hashSet) {
            if (p.coverageType.equalsIgnoreCase(type)) {
                System.out.println(p);
            }
        }
    }

    public void showDuplicates(List<Policy> policies) {
        Set<String> seen = new HashSet<>();
        Set<String> duplicates = new HashSet<>();
        for (Policy p : policies) {
            if (!seen.add(p.policyNumber)) {
                duplicates.add(p.policyNumber);
            }
        }
        for (Policy p : policies) {
            if (duplicates.contains(p.policyNumber)) {
                System.out.println(p);
            }
        }
    }

    public void performanceTest() {
        int size = 100_000;
        List<Policy> policyList = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            policyList.add(new Policy("P" + i, "Holder" + i, LocalDate.now().plusDays(i % 90), "Health", 1000 + i));
        }

        Set<Policy> hashSetTest = new HashSet<>();
        Set<Policy> linkedHashSetTest = new LinkedHashSet<>();
        Set<Policy> treeSetTest = new TreeSet<>();

        long start, end;

        start = System.nanoTime();
        hashSetTest.addAll(policyList);
        end = System.nanoTime();
        System.out.println("HashSet add time: " + (end - start) / 1_000_000 + " ms");

        start = System.nanoTime();
        linkedHashSetTest.addAll(policyList);
        end = System.nanoTime();
        System.out.println("LinkedHashSet add time: " + (end - start) / 1_000_000 + " ms");

        start = System.nanoTime();
        treeSetTest.addAll(policyList);
        end = System.nanoTime();
        System.out.println("TreeSet add time: " + (end - start) / 1_000_000 + " ms");
    }

    public static void main(String[] args) {
        PolicyManager manager = new PolicyManager();

        manager.addPolicy(new Policy("P101", "Alice", LocalDate.now().plusDays(10), "Health", 5000));
        manager.addPolicy(new Policy("P102", "Bob", LocalDate.now().plusDays(40), "Auto", 3000));
        manager.addPolicy(new Policy("P103", "Carol", LocalDate.now().plusDays(5), "Home", 7000));
        manager.addPolicy(new Policy("P101", "Duplicate", LocalDate.now().plusDays(30), "Health", 5000)); 

        System.out.println("\n--- All Unique Policies (HashSet) ---");
        manager.showAllPolicies(manager.hashSet);

        System.out.println("\n--- Expiring Soon (<=30 days) ---");
        manager.showExpiringSoon();

        System.out.println("\n--- Policies with Coverage Type: Health ---");
        manager.showByCoverageType("Health");

        System.out.println("\n--- Duplicates ---");
        manager.showDuplicates(new ArrayList<>(manager.hashSet));

        System.out.println("\n--- Performance Comparison ---");
        manager.performanceTest();
    }
}