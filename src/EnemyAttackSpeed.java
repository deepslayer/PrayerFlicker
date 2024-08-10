public enum EnemyAttackSpeed {
    FAST(2),    // 1.2 seconds
    MEDIUM(4),  // 2.4 seconds
    SLOW(6),    // 3.6 seconds
    VERY_SLOW(8); // 4.8 seconds

    private final int ticks;

    EnemyAttackSpeed(int ticks) {
        this.ticks = ticks;
    }

    public int getTicks() {
        return ticks;
    }
}
