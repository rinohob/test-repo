export const getCookieValue = (cookieName: string): string | undefined => {
  const cookies = document.cookie.split(';');
  for (let i = 0; i < cookies.length; i += 1) {
    const cookie = cookies[i].trim();
    if (cookie.startsWith(`${cookieName}=`)) {
      return cookie.substring(cookieName.length + 1);
    }
  }
  return undefined;
};

export const setCookie = (
  cookieName: string,
  cookieValue: string,
  expiryDays: number,
): void => {
  let cookieString = `${cookieName}=${cookieValue};path=/`;

  if (expiryDays !== undefined) {
    const d = new Date();
    d.setTime(d.getTime() + expiryDays * 24 * 60 * 60 * 1000);
    const expires = `expires=${d.toUTCString()}`;
    cookieString += `;${expires}`;
  }

  document.cookie = cookieString;
};

export const setRootStylePropertyValue = (
  property: string,
  value: string,
): void => {
  const rootEl = document.querySelector(':root') as HTMLElement;
  rootEl.style.setProperty(property, value);
};

export const isEditWcmMode = () => window.Granite && window.Granite.author;
