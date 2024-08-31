package medCashFlow.backend.model;

public enum Role {
    ADMIN("admin"),
    MANAGER("manager"),
    FINANCIAL_ANALYST("financial analyst"),
    DOCTOR("doctor");

    private String displayName;

    Role(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return this.displayName;
    }
}
