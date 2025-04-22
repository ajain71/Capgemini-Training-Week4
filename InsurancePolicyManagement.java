import java.time.LocalDate;
import java.util.*;

class Policy {
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

    public String toString() {
        return policyNumber + " - " + policyholderName + " - " + expiryDate + " - " + coverageType + " - " + premiumAmount;
    }

    public LocalDate getExpiryDate() {
        return expiryDate;
    }
}

public class InsurancePolicyManagement {
    Map<String, Policy> hashMap = new HashMap<>();
    Map<String, Policy> linkedHashMap = new LinkedHashMap<>();
    TreeMap<LocalDate, List<Policy>> treeMap = new TreeMap<>();

    public void addPolicy(Policy policy) {
        hashMap.put(policy.policyNumber, policy);
        linkedHashMap.put(policy.policyNumber, policy);
        treeMap.computeIfAbsent(policy.expiryDate, k -> new ArrayList<>()).add(policy);
    }

    public Policy getPolicyByNumber(String policyNumber) {
        return hashMap.get(policyNumber);
    }

    public List<Policy> getPoliciesExpiringSoon(int days) {
        LocalDate now = LocalDate.now();
        LocalDate threshold = now.plusDays(days);
        List<Policy> result = new ArrayList<>();
        for (Map.Entry<LocalDate, List<Policy>> entry : treeMap.subMap(now, true, threshold, true).entrySet()) {
            result.addAll(entry.getValue());
        }
        return result;
    }

    public List<Policy> getPoliciesByHolder(String holderName) {
        List<Policy> result = new ArrayList<>();
        for (Policy p : hashMap.values()) {
            if (p.policyholderName.equalsIgnoreCase(holderName)) {
                result.add(p);
            }
        }
        return result;
    }

    public void removeExpiredPolicies() {
        LocalDate now = LocalDate.now();
        Iterator<Map.Entry<String, Policy>> it = hashMap.entrySet().iterator();
        while (it.hasNext()) {
            Policy p = it.next().getValue();
            if (p.expiryDate.isBefore(now)) {
                it.remove();
                linkedHashMap.remove(p.policyNumber);
                List<Policy> list = treeMap.get(p.expiryDate);
                list.remove(p);
                if (list.isEmpty()) {
                    treeMap.remove(p.expiryDate);
                }
            }
        }
    }

    public void displayAllPolicies() {
        for (Policy p : linkedHashMap.values()) {
            System.out.println(p);
        }
    }

    public static void main(String[] args) {
        InsurancePolicyManagement system = new InsurancePolicyManagement();

        system.addPolicy(new Policy("P100", "Alice", LocalDate.now().plusDays(10), "Health", 4000));
        system.addPolicy(new Policy("P101", "Bob", LocalDate.now().plusDays(40), "Auto", 3500));
        system.addPolicy(new Policy("P102", "Alice", LocalDate.now().minusDays(5), "Home", 2000));

        System.out.println("\nAll Policies:");
        system.displayAllPolicies();

        System.out.println("\nRetrieve Policy by Number (P100):");
        System.out.println(system.getPolicyByNumber("P100"));

        System.out.println("\nPolicies Expiring in 30 Days:");
        for (Policy p : system.getPoliciesExpiringSoon(30)) {
            System.out.println(p);
        }

        System.out.println("\nPolicies for Alice:");
        for (Policy p : system.getPoliciesByHolder("Alice")) {
            System.out.println(p);
        }

        system.removeExpiredPolicies();

        System.out.println("\nPolicies After Removing Expired:");
        system.displayAllPolicies();
    }
}