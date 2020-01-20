/*
 * Sonatype Nexus (TM) Open Source Version
 * Copyright (c) 2008-present Sonatype, Inc.
 * All rights reserved. Includes the third-party code listed at http://links.sonatype.com/products/nexus/oss/attributions.
 *
 * This program and the accompanying materials are made available under the terms of the Eclipse Public License Version 1.0,
 * which accompanies this distribution and is available at http://www.eclipse.org/legal/epl-v10.html.
 *
 * Sonatype Nexus (TM) Professional Version is available from Sonatype, Inc. "Sonatype" and "Sonatype Nexus" are trademarks
 * of Sonatype, Inc. Apache Maven is a trademark of the Apache Software Foundation. M2eclipse is a trademark of the
 * Eclipse Foundation. All other trademarks are the property of their respective owners.
 */
package org.sonatype.nexus.datastore.mybatis;

import org.sonatype.nexus.crypto.PbeCipherFactory.PbeCipher;

import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.TypeHandler;

import static com.google.common.base.Preconditions.checkState;

/**
 * MyBatis {@link TypeHandler} that's aware of the database cipher.
 *
 * @since 3.next
 */
abstract class CipherAwareTypeHandler<T>
    extends BaseTypeHandler<T>
{
  PbeCipher cipher;

  /**
   * Enables encryption-at-rest for content generated by this handler using the given database cipher.
   */
  final void setCipher(final PbeCipher cipher) {
    // sanity check: the cipher should only be set once by the owning data store
    checkState(this.cipher == null || this.cipher.equals(cipher), "Cipher has already been set");
    this.cipher = cipher;
  }
}
