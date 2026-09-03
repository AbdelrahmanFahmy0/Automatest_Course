import { execSync } from "node:child_process";
import { env } from "node:process";

// Resolves a value from environment variables first (e.g. GitHub Actions), falling back
// to a local git command, and finally to a safe default. Never reads or exposes secrets.
function safeGit(command, fallback) {
    try {
        const result = execSync(command, { stdio: ["ignore", "pipe", "ignore"] }).toString().trim();
        return result || fallback;
    } catch {
        return fallback;
    }
}

const gitBranch = env.GITHUB_HEAD_REF || env.GITHUB_REF_NAME || safeGit("git rev-parse --abbrev-ref HEAD", "unknown");
const gitCommit = env.GITHUB_SHA ? env.GITHUB_SHA.substring(0, 12) : safeGit("git rev-parse --short HEAD", "unknown");
const ciRunUrl = (env.GITHUB_SERVER_URL && env.GITHUB_REPOSITORY && env.GITHUB_RUN_ID)
    ? `${env.GITHUB_SERVER_URL}/${env.GITHUB_REPOSITORY}/actions/runs/${env.GITHUB_RUN_ID}`
    : "local";

// This project uses the global/npx Allure 3 CLI (no local "allure" npm dependency),
// so the config object is exported directly instead of via defineConfig().
export default {
    name: "Automatest Course Report",
    output: "./test-output/reports/allure-report",
    // Single JSONL file tracking every historical test run (kept between CI runs via cache)
    historyPath: "./test-output/reports/.allure/history.jsonl",
    appendHistory: true,
    historyLimit: 30,
    // Environment information shown at the top of the report (no secrets included)
    variables: {
        "Operating System": env.RUNNER_OS || process.platform,
        "Git Branch": gitBranch,
        "Git Commit": gitCommit,
        "CI Run": ciRunUrl,
    },
    plugins: {
        awesome: {
            options: {
                reportName: "Automatest Course Report",
                singleFile: true,
                reportLanguage: 'en',
                groupBy: ['epic', 'feature', 'story']
            },
        },
    },
};