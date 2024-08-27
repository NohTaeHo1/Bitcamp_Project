'use client'
import { Inter } from "next/font/google";
import "./globals.css";
import dynamic from "next/dynamic";
import { parseCookies } from "nookies";
import DashHeader from "./components/common/modeule/dash-header";

const ReduxProvider = dynamic(() => import("@/redux/redux-provider"), {
  ssr: false
});

const inter = Inter({ subsets: ["latin"] });

// export const metadata: Metadata = {
//   title: "Create Next App",
//   description: "Generated by create next app",
// };



export default function RootLayout({
  children,
}: Readonly<{
  children: React.ReactNode;
}>) {


  return (
    <html lang="en">
      <body className={inter.className}>
        <div className="mt-100">
        <ReduxProvider > 
        {parseCookies().message === 'SUCCESS'}
          {children}</ReduxProvider>
        </div>
      </body>
    </html>
  );
}