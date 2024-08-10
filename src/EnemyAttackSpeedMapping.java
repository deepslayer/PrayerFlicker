public class EnemyAttackSpeedMapping {

    public static EnemyAttackSpeed getAttackSpeedForEnemy(String enemyName) {
        switch (enemyName.toLowerCase()) {
            case "hill giant":
                return EnemyAttackSpeed.SLOW;
            case "green dragon":
                return EnemyAttackSpeed.MEDIUM;
            case "guard":
                return EnemyAttackSpeed.SLOW;

            default:
                return EnemyAttackSpeed.MEDIUM;
        }
    }
}
