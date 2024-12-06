// Declare a global augmentation of the Window interface
declare global {
    interface Window {
        STORYBOOK__MODE: boolean;
        Granite: { author: string };
    }
}
export {};
