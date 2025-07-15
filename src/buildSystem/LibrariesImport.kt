package buildSystem

/**
 * ### How Libraries Are Imported via `build.gradle` in Android Projects?
 *
 * 1. **Declaring Dependencies**
 *    You define dependencies in your `build.gradle (Module)` file using:
 *
 *        dependencies {
 *            implementation("com.squareup.retrofit2:retrofit:2.9.0")
 *            implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.7.3")
 *        }
 *
 *    Each dependency follows the format: `group:artifact:version`
 *    For example: `com.squareup.retrofit2:retrofit:2.9.0`
 *
 * 2. **Gradle Repositories**
 *    Gradle looks for dependencies in specified repositories:
 *
 *        repositories {
 *            google()
 *            mavenCentral()
 *        }
 *
 *    Common repositories include:
 *    - `google()` – AndroidX, Google Play Services
 *    - `mavenCentral()` – Most Java/Kotlin open-source libraries
 *
 * 3. **What Happens Internally**
 *    - Gradle downloads the corresponding `.jar` or `.aar` from the repository.
 *    - Files are stored locally in `.gradle/caches`.
 *    - During build time, they are linked and compiled with your project.
 *    - You can import them directly in your Kotlin/Java code:
 *
 *          import retrofit2.Retrofit
 *
 * 4. **JAR vs AAR**
 *    - `.jar`: Contains compiled Java/Kotlin bytecode only.
 *    - `.aar`: Used for Android libraries that include resources (e.g., layouts, drawables, manifests).
 *
 * 5. **Transitive Dependencies**
 *    Gradle automatically resolves and downloads any libraries required by the libraries you declare.
 *    These are known as *transitive dependencies*.
 *
 * **Summary**
 * - Libraries are declared in `build.gradle`.
 * - Gradle downloads them from repositories like Maven Central.
 * - Stored in Gradle cache.
 * - Automatically linked and available for use in your app.
 */
fun main(){}
/**
 * ## 🔧 Source Code → Intermediate → Executable: Build Process Breakdown
 *
 * In any compiled language (like Kotlin or Java), code goes through several stages before becoming an executable form.
 * In Android development, this path ends in an `.apk` or `.aab`.
 *
 * ---
 *
 * ### 🧾 1. Source Code
 * - The human-readable Kotlin or Java code you write.
 * - Files: `.kt`, `.java`, `.xml` (layouts), and resource files (e.g., drawables, strings).
 *
 * ---
 *
 * ### ⚙️ 2. Intermediate Representations
 *
 * #### 🔸 Kotlin → Java Bytecode
 * - Kotlin code is compiled by `kotlinc` to `.class` files (Java bytecode).
 * - If you're using annotation processors (e.g., Dagger, Room), `kapt` also generates intermediate `.java` files.
 *
 * #### 🔸 Java Bytecode → DEX
 * - The `.class` files are converted to **DEX (Dalvik Executable)** format via the **D8** tool.
 * - DEX files are optimized for the Android Runtime (ART) or legacy Dalvik VM.
 *
 * #### 🔸 Resources Compiled
 * - XML resources are compiled via **AAPT2** (Android Asset Packaging Tool).
 * - Resource merging and manifest merging also happen at this stage.
 * - Output includes files like `R.java`, `BuildConfig.java`, and resource binaries.
 *
 * ---
 *
 * ### 📦 3. Executable Output
 *
 * #### ✅ APK (Android Package)
 * - Contains:
 *   - `.dex` files
 *   - Compiled resources
 *   - Manifest
 *   - Native `.so` libraries (if any)
 * - It is signed and installable on Android devices.
 *
 * #### ✅ AAB (Android App Bundle)
 * - Newer format for Play Store uploads.
 * - Google Play uses this to generate optimized APKs per device configuration.
 *
 * ---
 *
 * ### 🧠 Summary Flow
 * ```
 * Kotlin/Java/XML Source
 *         ↓
 *  KotlinC / JavaC / AAPT2
 *         ↓
 *     .class + resources
 *         ↓
 *       D8 → .dex
 *         ↓
 *     Merge + Package
 *         ↓
 *    Signed .apk / .aab
 * ```
 *
 * ---
 *
 * ### 📌 Bonus Tools Involved
 * - `kotlinc` / `javac` → Compilers for source code
 * - `kapt` → Kotlin annotation processor
 * - `D8` / `R8` → Dexing and shrinking tools
 * - `AAPT2` → Compiles and links Android resources
 * - `ZipAlign` / `ApkSigner` → Optimize and sign the APK
 */
fun x(){}
