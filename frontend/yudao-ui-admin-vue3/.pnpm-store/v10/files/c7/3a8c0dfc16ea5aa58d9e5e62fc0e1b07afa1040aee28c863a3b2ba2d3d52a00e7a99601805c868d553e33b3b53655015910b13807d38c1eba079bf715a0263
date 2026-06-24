import { LooseRuleDefinition } from "@typescript-eslint/utils/ts-eslint";
import { Linter } from "eslint";

//#region src/types.d.ts
type UnocssEnforceClassCompile = [] | [{
  prefix?: string;
  enableFix?: boolean;
}];
type UnocssOrder = [] | [{
  unoFunctions?: string[];
  unoVariables?: string[];
}];
interface UnoCSSEslintPlugin {
  rules: Record<string, LooseRuleDefinition>;
}
interface UnoCSSEslintFlatConfig extends Linter.Config {
  plugins: {
    unocss: any;
  };
  rules: {
    readonly 'unocss/order': Linter.RuleEntry<UnocssOrder>;
    readonly 'unocss/order-attributify': Linter.RuleEntry<[]>;
    readonly 'unocss/blocklist'?: Linter.RuleEntry<[]>;
    readonly 'unocss/enforce-class-compile'?: Linter.RuleEntry<UnocssEnforceClassCompile>;
  };
}
interface UnoCSSEslintRecommendedConfig extends Linter.LegacyConfig {
  rules: {
    readonly '@unocss/order': Linter.RuleEntry<UnocssOrder>;
    readonly '@unocss/order-attributify': Linter.RuleEntry<[]>;
    readonly '@unocss/blocklist'?: Linter.RuleEntry<[]>;
    readonly '@unocss/enforce-class-compile'?: Linter.RuleEntry<UnocssEnforceClassCompile>;
  };
}
interface UnoCSSEslintConfigs {
  recommended: UnoCSSEslintRecommendedConfig;
  flat: UnoCSSEslintFlatConfig;
}
interface UnoCSSEslintPluginModule extends UnoCSSEslintPlugin {
  configs: UnoCSSEslintConfigs;
}
declare module '@typescript-eslint/utils/ts-eslint' {
  interface SharedConfigurationSettings {
    unocss?: {
      configPath?: string;
    };
  }
}
//#endregion
//#region src/index.d.ts
declare const configs: UnoCSSEslintConfigs;
declare const eslintPlugin: UnoCSSEslintPluginModule;
//#endregion
export { type UnoCSSEslintConfigs, type UnoCSSEslintFlatConfig, type UnoCSSEslintPlugin, type UnoCSSEslintPluginModule, type UnoCSSEslintRecommendedConfig, configs, eslintPlugin as default };