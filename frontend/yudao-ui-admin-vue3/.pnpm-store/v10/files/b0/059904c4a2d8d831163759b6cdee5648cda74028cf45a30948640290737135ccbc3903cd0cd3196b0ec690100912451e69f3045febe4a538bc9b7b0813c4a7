import { f as VueTypesDefaults, g as VueTypesConfig } from './shared/vue-types.d8e57a80.cjs';
export { V as VueTypeDef, a as VueTypeValidableDef, h as config } from './shared/vue-types.d8e57a80.cjs';
import 'vue';

type TypeShim = <T = any>(...args: any[]) => any;
declare const any: TypeShim;
declare const func: TypeShim;
declare const bool: () => any;
declare const string: TypeShim;
declare const number: TypeShim;
declare const array: TypeShim;
declare const object: TypeShim;
declare const symbol: () => any;
declare const integer: TypeShim;
declare const oneOf: TypeShim;
declare const custom: TypeShim;
declare const instanceOf: TypeShim;
declare const oneOfType: TypeShim;
declare const arrayOf: TypeShim;
declare const objectOf: TypeShim;
declare const shape: TypeShim;
declare const nullable: TypeShim;
declare function fromType(name: string, source: any, props?: any): any;
declare const toValidableType: <T>(name: string, props: any) => any;
declare const toType: <T>(name: string, props: any) => any;
declare function createTypes(defs?: Partial<VueTypesDefaults>): {
    new (): {};
    defaults: Partial<VueTypesDefaults>;
    sensibleDefaults: boolean | Partial<VueTypesDefaults>;
    config: VueTypesConfig;
    readonly any: any;
    readonly func: any;
    readonly bool: any;
    readonly string: any;
    readonly number: any;
    readonly array: any;
    readonly object: any;
    readonly symbol: any;
    readonly integer: any;
    readonly nullable: any;
    oneOf: TypeShim;
    custom: TypeShim;
    instanceOf: TypeShim;
    oneOfType: TypeShim;
    arrayOf: TypeShim;
    objectOf: TypeShim;
    shape: TypeShim;
    extend<T = any>(props: any): T;
    utils: {
        toType: (...args: any[]) => any;
        validate: (...args: any[]) => boolean;
    };
};
declare function validateType<T, U>(_type: T, _value: U, _silent?: boolean): string | boolean;
declare const VueTypes_base: {
    new (): {};
    defaults: Partial<VueTypesDefaults>;
    sensibleDefaults: boolean | Partial<VueTypesDefaults>;
    config: VueTypesConfig;
    readonly any: any;
    readonly func: any;
    readonly bool: any;
    readonly string: any;
    readonly number: any;
    readonly array: any;
    readonly object: any;
    readonly symbol: any;
    readonly integer: any;
    readonly nullable: any;
    oneOf: TypeShim;
    custom: TypeShim;
    instanceOf: TypeShim;
    oneOfType: TypeShim;
    arrayOf: TypeShim;
    objectOf: TypeShim;
    shape: TypeShim;
    extend<T = any>(props: any): T;
    utils: {
        toType: (...args: any[]) => any;
        validate: (...args: any[]) => boolean;
    };
};
declare class VueTypes/*#__PURE__*/  extends VueTypes_base {
}

export { any, array, arrayOf, bool, createTypes, custom, VueTypes as default, fromType, func, instanceOf, integer, nullable, number, object, objectOf, oneOf, oneOfType, shape, string, symbol, toType, toValidableType, validateType };
