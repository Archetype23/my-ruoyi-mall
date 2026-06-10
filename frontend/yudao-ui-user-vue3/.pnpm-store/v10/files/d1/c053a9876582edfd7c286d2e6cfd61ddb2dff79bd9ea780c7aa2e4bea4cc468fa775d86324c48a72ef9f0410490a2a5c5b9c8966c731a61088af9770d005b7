import { P as PropOptions, V as VueTypeDef, a as VueTypeValidableDef, I as InferType, b as ValidatorFunction, c as VueProp, d as Prop, C as Constructor, e as VueTypeShape, f as VueTypesDefaults, g as VueTypesConfig } from './shared/vue-types.d8e57a80.cjs';
export { i as VueTypeLooseShape, h as config } from './shared/vue-types.d8e57a80.cjs';
import 'vue';

/**
 * Validates a given value against a prop type object.
 *
 * If `silent` is `false` (default) will return a boolean. If it is set to `true`
 * it will return `true` on success or a string error message on failure
 *
 * @param {Object|*} type - Type to use for validation. Either a type object or a constructor
 * @param {*} value - Value to check
 * @param {boolean} silent - Silence warnings
 */
declare function validateType<T, U>(type: T, value: U, silent?: boolean): string | boolean;
/**
 * Adds `isRequired` and `def` modifiers to an object
 *
 * @param {string} name - Type internal name
 * @param {object} obj - Object to enhance
 */
declare function toType<T = any>(name: string, obj: PropOptions<T>): VueTypeDef<T>;
/**
 * Like `toType` but also adds the `validate()` method to the type object
 *
 * @param {string} name - Type internal name
 * @param {object} obj - Object to enhance
 */
declare function toValidableType<T = any>(name: string, obj: PropOptions<T>): VueTypeValidableDef<T>;
/**
 * Return a new VueTypes type using another type as base.
 *
 * Properties in the `props` object will overwrite those defined in the source one
 * expect for the `validator` function. In that case both functions will be executed in series.
 *
 * @param name - Name of the new type
 * @param source - Source type
 * @param props - Custom type properties
 */
declare function fromType<T extends VueTypeDef<any>>(name: string, source: T): T;
declare function fromType<T extends VueTypeDef<any>, V extends PropOptions<InferType<T>>>(name: string, source: T, props: V): Omit<T, keyof V> & V;

declare const any: <T = any>() => VueTypeValidableDef<T, ValidatorFunction<T>>;
declare const func: <T extends (...args: any[]) => any>() => VueTypeValidableDef<T, ValidatorFunction<T>>;
declare const bool: () => VueTypeValidableDef<boolean, ValidatorFunction<boolean>>;
declare const string: <T extends string = string>() => VueTypeValidableDef<T, ValidatorFunction<T>>;
declare const number: <T extends number = number>() => VueTypeValidableDef<T, ValidatorFunction<T>>;
declare const array: <T>() => VueTypeValidableDef<T[], ValidatorFunction<T[]>>;
declare const object: <T extends Record<string, any>>() => VueTypeValidableDef<T, ValidatorFunction<T>>;
declare const integer: <T extends number = number>() => VueTypeDef<T>;
declare const symbol: () => VueTypeDef<symbol>;
declare const nullable: () => PropOptions<null>;

declare function custom<T>(validatorFn: ValidatorFunction<T>, warnMsg?: string): VueTypeDef<T>;

declare function oneOf<D, T extends readonly D[] = readonly D[]>(arr: T): VueTypeDef<T[number]>;

declare function oneOfType<D extends V, U extends VueProp<any> | Prop<any> = any, V = InferType<U>>(arr: U[]): VueTypeDef<D>;

declare function arrayOf<T extends VueProp<any> | Prop<any>>(type: T): VueTypeDef<InferType<T>[]>;

declare function instanceOf<C extends Constructor>(instanceConstructor: C): VueTypeDef<InstanceType<C>>;

declare function objectOf<T extends VueProp<any> | Prop<any>>(type: T): VueTypeDef<Record<string, InferType<T>>>;

declare function shape<T extends object>(obj: {
    [K in keyof T]: Prop<T[K]> | VueProp<T[K]>;
}): VueTypeShape<T>;

declare function createTypes(defs?: Partial<VueTypesDefaults>): {
    new (): {};
    defaults: Partial<VueTypesDefaults>;
    sensibleDefaults: boolean | Partial<VueTypesDefaults>;
    config: VueTypesConfig;
    readonly any: VueTypeValidableDef<any, ValidatorFunction<any>>;
    readonly func: VueTypeValidableDef<(...args: any[]) => any, ValidatorFunction<(...args: any[]) => any>> & {
        default: (...args: any[]) => any;
    };
    readonly bool: VueTypeValidableDef<boolean, ValidatorFunction<boolean>>;
    readonly string: VueTypeValidableDef<string, ValidatorFunction<string>> & {
        default: string;
    };
    readonly number: VueTypeValidableDef<number, ValidatorFunction<number>> & {
        default: number;
    };
    readonly array: VueTypeValidableDef<unknown[], ValidatorFunction<unknown[]>> & {
        default: () => unknown[];
    };
    readonly object: VueTypeValidableDef<Record<string, any>, ValidatorFunction<Record<string, any>>> & {
        default: () => Record<string, any>;
    };
    readonly integer: VueTypeDef<number> & {
        default: number;
    };
    readonly symbol: VueTypeDef<symbol>;
    readonly nullable: PropOptions<null, null>;
    readonly custom: typeof custom;
    readonly oneOf: typeof oneOf;
    readonly instanceOf: typeof instanceOf;
    readonly oneOfType: typeof oneOfType;
    readonly arrayOf: typeof arrayOf;
    readonly objectOf: typeof objectOf;
    readonly shape: typeof shape;
    extend(...args: any[]): void;
    utils: {
        validate<T, U>(value: T, type: U): boolean;
        toType<T = unknown, Validable extends boolean = false>(name: string, obj: PropOptions<T>, validable?: Validable): Validable extends true ? VueTypeValidableDef<T> : VueTypeDef<T>;
    };
};
declare const VueTypes_base: {
    new (): {};
    defaults: Partial<VueTypesDefaults>;
    sensibleDefaults: boolean | Partial<VueTypesDefaults>;
    config: VueTypesConfig;
    readonly any: VueTypeValidableDef<any, ValidatorFunction<any>>;
    readonly func: VueTypeValidableDef<(...args: any[]) => any, ValidatorFunction<(...args: any[]) => any>> & {
        default: (...args: any[]) => any;
    };
    readonly bool: VueTypeValidableDef<boolean, ValidatorFunction<boolean>>;
    readonly string: VueTypeValidableDef<string, ValidatorFunction<string>> & {
        default: string;
    };
    readonly number: VueTypeValidableDef<number, ValidatorFunction<number>> & {
        default: number;
    };
    readonly array: VueTypeValidableDef<unknown[], ValidatorFunction<unknown[]>> & {
        default: () => unknown[];
    };
    readonly object: VueTypeValidableDef<Record<string, any>, ValidatorFunction<Record<string, any>>> & {
        default: () => Record<string, any>;
    };
    readonly integer: VueTypeDef<number> & {
        default: number;
    };
    readonly symbol: VueTypeDef<symbol>;
    readonly nullable: PropOptions<null, null>;
    readonly custom: typeof custom;
    readonly oneOf: typeof oneOf;
    readonly instanceOf: typeof instanceOf;
    readonly oneOfType: typeof oneOfType;
    readonly arrayOf: typeof arrayOf;
    readonly objectOf: typeof objectOf;
    readonly shape: typeof shape;
    extend(...args: any[]): void;
    utils: {
        validate<T, U>(value: T, type: U): boolean;
        toType<T = unknown, Validable extends boolean = false>(name: string, obj: PropOptions<T>, validable?: Validable): Validable extends true ? VueTypeValidableDef<T> : VueTypeDef<T>;
    };
};
declare class VueTypes/*#__PURE__*/  extends VueTypes_base {
}

type VueTypesInterface = ReturnType<typeof createTypes>;

export { VueTypeDef, VueTypeShape, VueTypeValidableDef, type VueTypesInterface, any, array, arrayOf, bool, createTypes, custom, VueTypes as default, fromType, func, instanceOf, integer, nullable, number, object, objectOf, oneOf, oneOfType, shape, string, symbol, toType, toValidableType, validateType };
