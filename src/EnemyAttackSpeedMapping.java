public class EnemyAttackSpeedMapping {

    public static EnemyAttackSpeed getAttackSpeedForEnemy(String enemyName) {
        switch (enemyName.toLowerCase()) {
            case "moss giant":
                return EnemyAttackSpeed.SLOW;
            case "green dragon":
                return EnemyAttackSpeed.MEDIUM;
            case "guard":
                return EnemyAttackSpeed.SLOW;
            case "goblin":
                return EnemyAttackSpeed.MEDIUM;
            default:
                return EnemyAttackSpeed.MEDIUM;
        }
    }
}
